

$(document).ready(function() {

    $("#calcular").on("click", function(e) {
        e.preventDefault()
        let data = {}
        data["from"] = $("#from").val();
        data["to"] = $("#to").val();
        data["tempObj"] = $("#tempObj").val();
        console.log(data)
        $.ajax({
            type:"POST",
            contentType: "application/json",
            url: "temp/getData",
            data: JSON.stringify(data),
            dataType:'json',
            timeout: 600000,
            success: function(data){
                alert("Proceso Exitoso!");
               $(".minTemp").text(data.minTemp)
                $(".maxTemp").text(data.maxTemp)
                $(".seconds").text(data.seconds)

             },
             error:function(response){
                 alert("Rango de Fechas Incorrectas!");
             }
        })
      })

    
});

window.addEventListener('DOMContentLoaded', (evento) => {
    const hoy_fecha = new Date().toISOString().substring(0, 10);
    document.querySelectorAll("input[type='date'][max='hoy']")
    .forEach(elemento => {
        elemento.max = hoy_fecha;
    });
});