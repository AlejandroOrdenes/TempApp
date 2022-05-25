package com.prueba.dynae.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.dynae.dto.DataResultDTO;
import com.prueba.dynae.dto.FormDTO;
import com.prueba.dynae.dto.TempDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TempDataService {

    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TempDataService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public DataResultDTO getData(FormDTO formDTO) throws ParseException {
        String url = "http://data-env.eba-pwemrg4q.us-east-1.elasticbeanstalk.com/data/sensorElement/8/measurement?from="+formDTO.getFrom()+"T04:00:00.000Z&to="+ formDTO.getTo()+"T03:59:59.000Z&timeUnit=SEC";
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);

        Object[] objects = responseEntity.getBody();

        List<TempDataDTO> dataAPI = Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, TempDataDTO.class))
                .collect(Collectors.toList());

        List<TempDataDTO> dataSortDto = sortData(dataAPI);
        System.out.println(dataSortDto);

        DataResultDTO dataResult = new DataResultDTO();
        dataResult.setMaxTemp(getMaxTemp(dataSortDto));
        dataResult.setMinTemp(getMinTemp(dataSortDto));
        dataResult.setTempObjt((formDTO.getTempObj()));
        dataResult.setSeconds(getSecond(dataSortDto, formDTO.getTempObj()));

        return dataResult;
    }

    public List<TempDataDTO> sortData(List<TempDataDTO> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = data.size() - 1; j > i; j--) {
                if (data.get(i).getMagnitude() > data.get(j).getMagnitude()) {
                    TempDataDTO tmp = data.get(i);
                    data.set(i,data.get(j));
                    data.set(j,tmp);
                }
            }
        }
        return data;

    }
    public Double getSecond(List<TempDataDTO> data, Double temp) throws ParseException {

        int lastIdx = data.size()-1;
        String end = data.get(lastIdx).getTimestamp();
        String init = data.get(0).getTimestamp();
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getMagnitude() > temp) {
                init = data.get(i).getTimestamp();
                break;
            } else {
                end = data.get(lastIdx).getTimestamp();
                init = data.get(lastIdx).getTimestamp();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateInit = LocalDateTime.parse(init, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(end, formatter);

         Double seconds;

        if (dateInit.isAfter(dateEnd)) {
            seconds = Double.valueOf((ChronoUnit.SECONDS.between(dateEnd, dateInit)%86400));
        } else {
            seconds = Double.valueOf((ChronoUnit.SECONDS.between(dateInit, dateEnd)%86400));
        }

        return seconds;
    }

    public Double getMaxTemp(List<TempDataDTO> data) {
        Double max = data.get(0).getMagnitude();
        for (int i = 0; i < data.size(); i++) {
            var temp = data.get(i).getMagnitude();
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public Double getMinTemp(List<TempDataDTO> data) {
        Double min = data.get(0).getMagnitude();
        for (int i = 0; i < data.size(); i++) {
            Double temp = data.get(i).getMagnitude();
            if (temp < min) {
                min = temp;
            }

        }
        return min;
    }


}


