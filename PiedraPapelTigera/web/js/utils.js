function obtenerDatosLenguaje() {
    var lenguaje = {
        "sProcessing": "Procesando...",
        "sLengthMenu": "Mostrar _MENU_ registros",
        "sZeroRecords": "No se encontraron resultados",
        "sEmptyTable": "Ningún dato disponible en esta tabla",
        "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
        "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
        "sInfoPostFix": "",
        "sSearch": "Buscar:",
        "sUrl": "",
        "sInfoThousands": ",",
        "sLoadingRecords": "Cargando...",
        "oPaginate": {
            "sFirst": "Primero",
            "sLast": "Último",
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        },
        "oAria": {
            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
        }
    };
    return lenguaje;
}

function obtenerParametro(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
    return null;
}

function asignarNombreEmpresa() {
    var nombre = localStorage.getItem("nombreEmpresa");
    document.getElementById("logoEmpresa").innerHTML = nombre;
}

function obtenerFecha(fecha) {
    var dd = fecha.getDate();
    var mm = fecha.getMonth() + 1;


    var yyyy = fecha.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    var fecha = dd + '/' + mm + '/' + yyyy;
    return fecha;

}

function obtenerHora() {
    var fecha = new Date();
    var hh = fecha.getHours();
    var mi = fecha.getMinutes();
    var ss = fecha.getSeconds();

    var ampm = hh >= 12 ? 'PM' : 'AM';
    hh = hh % 12;
    hh = hh ? hh : 12; // the hour '0' should be '12'
    mi = mi < 10 ? '0' + mi : mi;
    ss = ss < 10 ? '0' + ss : ss;

    return ' ' + hh + ':' + mi + ':' + ss + ' ' + ampm;

}

function validarMesENG(mes_p) {
    var meses = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
    for (var i = 0; i < meses.length; i++) {
        if (mes_p == meses[i]) {
            return i;
        }
    }
}
function validarMesESP(mes_p) {
    var meses = ["ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"];
    for (var i = 0; i < meses.length; i++) {
        if (mes_p == meses[i]) {
            return i;
        }
    }
}

function limpiarLocalStorageBasico() {
    localStorage.removeItem("cliente");
    localStorage.removeItem("anio");
    localStorage.removeItem("marca");
    localStorage.removeItem("modelo");
    localStorage.removeItem("moneda");
    localStorage.removeItem("nombreCliente");
    localStorage.removeItem("orden");
    localStorage.removeItem("placa");
    localStorage.removeItem("vehiculo");
}

function cargarArchivo(directorio, nombreSalida, file_data) {
    var form_data = new FormData();
    form_data.append(directorio, "");
    form_data.append(nombreSalida, "");
    form_data.append('file', file_data);
    $.ajax({
        url: 'UT_Cargar',
        dataType: 'text',
        cache: false,
        contentType: false,
        processData: false,
        async: false,
        data: form_data,
        type: 'post',
        success: function (response) {
            console.log("Carga correcta");
        }
    });


}