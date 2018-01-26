/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

Parse.initialize("TQhl7DbLKccsTnRgMR6rtlCSWgo7KTdfGfGDlx32", "q6yDi6SBuC3LhL8ohXrCLFEFvdOdZY6kK8sMX3KL");
Parse.serverURL = "https://parseapi.back4app.com/";

var currentUser = Parse.User.current();
var restaurant = "";
//var restaurant;
if (!currentUser) {
// do stuff with the user
    window.location = "index.html";
}

var query = new Parse.Query("Restaurant");
query.equalTo('owner', Parse.User.current());
query.find({
  success: function(results) {
    alert("Successfully retrieved " + results.length + " id");
    // Do something with the returned Parse.Object values
    for (var i = 0; i < results.length; i++) {
      restaurant = results[i].get("name");
      alert(object.id + ' - ' + object.get('name'));
    }
  },
  error: function(error) {
    alert("Error: " + error.code + " " + error.message);
  }
});

function logout() {
    Parse.User.logOut().then(() => {
        window.location = "index.html";
    });
}

$(document).ready(function ($) {
    var random_id = function () {
        var id_num = Math.random().toString(9).substr(2, 3);
        var id_str = Math.random().toString(36).substr(2);
        return id_num + id_str;
    };
    
    
    var table = '';
    table += '<h2><div class="restaurant_name" edit_type="click" col_name="fname">' + restaurant + '</div>';
    table += '<span class="btn_edit_name"> <a href="#" class="btn btn-link">Editar</a> </span>';
    table += '<span class="btn_save_name"> <a href="#" class="btn btn-link">Salvar</a> | </span>';
    table += '<span class="btn_cancel_name"> <a href="#" class="btn btn-link"> Cancelar </a> </span></h2>';
    table += '<div class="row">';
    table += '<div class="col-xs-12">';
    table += '<table class="table table-hover">';
    table += '<thead>';
    table += '<tr>';
    table += '<th>Nome</th>';
    table += '<th>Ingredientes</th>';
    table += '<th>Calorias</th>';
    table += '<th>Preço</th>';
    table += '<th>Gerenciar</th> </tr> </thead>';
    for (var i = 0, max = 4; i < max; i++) {
        var row_id = random_id();
        table += '<tr row_id="' + row_id + '">';
        for (var j = 0, max = 4; j < max; j++) {
            table += '<td><div class="row_data" edit_type="click" col_name="fname">' + (j * i) + '</div>' + '</td>';
        }

        //Editar ---> Inicio
        table += '<td>';
        table += '<span class="btn_edit"> <a href="#" class="btn btn-link" row_id="' + row_id + '"> Editar</a> </span>';
        //Só aparece quando o botão editar é clikado
        table += '<span class="btn_save"> <a href="#" class="btn btn-link" row_id="' + row_id + '"> Salvar</a> | </span>';
        table += '<span class="btn_cancel"> <a href="#" class="btn btn-link" row_id="' + row_id + '"> Cancelar</a> </span>';
        table += '</td>';
        //Editar ---> Fim
        table += '</tr>';
    }

    table += '</table>';
    table += '</div>';
    table += '</div>';
    $('.tbl_user_data').html(table);
    $('.btn_save').hide();
    $('.btn_cancel').hide();
    $('.btn_save_name').hide();
    $('.btn_cancel_name').hide();

    //Botão Editar <----> Inicio
    $(document).on('click', '.btn_edit', function (event) {

        event.preventDefault();
        var tbl_row = $(this).closest('tr');

        var row_id = tbl_row.attr('row_id');

        tbl_row.find('.btn_save').show();
        tbl_row.find('.btn_cancel').show();

        tbl_row.find('.btn_edit').hide();

        tbl_row.find('.row_data').attr('contenteditable', 'true')
                .attr('edit_type', 'button').addClass('bg-warning').css('padding', '3px');

        //--->add the original entry > start
        tbl_row.find('.row_data').each(function (index, val)
        {
            //this will help in case user decided to click on cancel button
            $(this).attr('original_entry', $(this).html());
        });
        //--->add the original entry > end
    });
    //Botão Editar <----> Fim
    //
    //Botão Editar Nome Restaurante <----> Inicio
    $(document).on('click', '.btn_edit_name', function (event) {

        event.preventDefault();
        var name_title = $(this).closest('h2');

        //var row_id = tbl_row.attr('row_id');

        name_title.find('.btn_save_name').show();
        name_title.find('.btn_cancel_name').show();

        name_title.find('.btn_edit_name').hide();

        name_title.find('.restaurant_name').attr('contenteditable', 'true')
                .attr('edit_type', 'button').addClass('bg-warning').css('padding', '3px');

        //--->add the original entry > start
        name_title.find('.restaurant_name').each(function (index, val)
        {
            //this will help in case user decided to click on cancel button
            $(this).attr('original_entry', $(this).html());
        });
        //--->add the original entry > end
    });
    //Botão Editar Nome Restaurante <----> Fim

    //Botão Cancelar <----> Inicio
    $(document).on('click', '.btn_cancel', function (event) {
        event.preventDefault();

        var tbl_row = $(this).closest('tr');
        var row_id = tbl_row.attr('row_id');

        tbl_row.find('.btn_save').hide();
        tbl_row.find('.btn_cancel').hide();

        tbl_row.find('.btn_edit').show();

        tbl_row.find('.row_data').attr('edit_type', 'click')
                .removeAttr('contenteditable').removeClass('bg-warning').css('padding', '');

        tbl_row.find('.row_data').each(function (index, val)
        {
            $(this).html($(this).attr('original_entry'));
        });


    });
    //Botão Cancelar <----> Fim
    //
    //Botão Cancelar Nome Restaurante <----> Inicio
    $(document).on('click', '.btn_cancel_name', function (event) {
        event.preventDefault();

        var name_title = $(this).closest('h2');
        //var row_id = tbl_row.attr('row_id');

        name_title.find('.btn_save_name').hide();
        name_title.find('.btn_cancel_name').hide();

        name_title.find('.btn_edit_name').show();

        name_title.find('.restaurant_name').attr('edit_type', 'click')
                .removeAttr('contenteditable').removeClass('bg-warning').css('padding', '');

        name_title.find('.restaurant_name').each(function (index, val)
        {
            $(this).html($(this).attr('original_entry'));
        });


    });
    //Botão Cancelar Nome Restaurante <----> Fim

    //Botão Salvar <----> Inicio
    $(document).on('click', '.btn_save', function (event)
    {
        event.preventDefault();

        var tbl_row = $(this).closest('tr');
        var row_id = tbl_row.attr('row_id');

        tbl_row.find('.btn_save').hide();
        tbl_row.find('.btn_cancel').hide();

        tbl_row.find('.btn_edit').show();


        //make the whole row editable
        tbl_row.find('.row_data')
                .attr('edit_type', 'click')
                .removeAttr('contenteditable')
                .removeClass('bg-warning')
                .css('padding', '');

        //--->get row data > start
        var arr = {};
        tbl_row.find('.row_data').each(function (index, val)
        {
            var col_name = $(this).attr('col_name');
            var col_val = $(this).html();
            arr[col_name] = col_val;
        });
        //--->get row data > end

        //use the "arr"	object for your ajax call
        $.extend(arr, {row_id: row_id});

        //out put to show
        $('.post_msg').html('<pre class="bg-success">' + JSON.stringify(arr, null, 2) + '</pre>');
    });
    //Botão Salvar <----> Fim
    //
    //Botão Salvar Nome Restaurante <----> Inicio
    $(document).on('click', '.btn_save_name', function (event)
    {
        event.preventDefault();

        var name_title = $(this).closest('h2');
        //var row_id = tbl_row.attr('row_id');

        name_title.find('.btn_save_name').hide();
        name_title.find('.btn_cancel_name').hide();

        name_title.find('.btn_edit_name').show();


        //make the whole row editable
        name_title.find('.restaurant_name')
                .attr('edit_type', 'click')
                .removeAttr('contenteditable')
                .removeClass('bg-warning')
                .css('padding', '');

        //--->get row data > start
        //var arr = {};
        name_title.find('.restaurant_name').each(function (index, val)
        {
            var col_name = $(this).attr('col_name');
            var col_val = $(this).html();
            //arr[col_name] = col_val;
        });
        //--->get row data > end

        //use the "arr"	object for your ajax call
        //$.extend(arr, {row_id: row_id});

        //out put to show
        //$('.post_msg').html('<pre class="bg-success">' + JSON.stringify(arr, null, 2) + '</pre>');
    });
    //Botão Salvar Nome Restaurante <----> Fim

});

