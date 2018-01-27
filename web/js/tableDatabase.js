/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('.btn-create').hide();

Parse.initialize("TQhl7DbLKccsTnRgMR6rtlCSWgo7KTdfGfGDlx32", "q6yDi6SBuC3LhL8ohXrCLFEFvdOdZY6kK8sMX3KL");
Parse.serverURL = "https://parseapi.back4app.com/";

var currentUser = Parse.User.current();
var restaurantObject;

if (!currentUser) {

    window.location = "index.html";
}

var query = new Parse.Query("Restaurant");
query.equalTo('owner', Parse.User.current());
query.find({
    success: function (results) {

        for (var i = 0; i < results.length; i++) {
            restaurantObject = results[i];
        }

        var query = new Parse.Query("Food");
        query.equalTo("fromRestaurant", restaurantObject);

        query.find({
            success: function (results) {
                var table = '';
                table += '<h2><div id="restaurant" class="restaurant_name" edit_type="click" col_name="fname">' + restaurantObject.get('name') + '</div>';
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

                for (var i = 0; i < results.length; i++) {
                    var object = results[i];

                    table += '<tr row_id="' + object.id + '">';

                    table += '<td><div class="row_data" edit_type="click" col_name="fname">' + object.get('foodname') + '</div>' + '</td>';
                    table += '<td><div class="row_data" edit_type="click" col_name="fname">' + object.get('ingredients') + '</div>' + '</td>';
                    table += '<td><div class="row_data" edit_type="click" col_name="fname">' + object.get('calories') + '</div>' + '</td>';
                    table += '<td><div class="row_data" edit_type="click" col_name="fname">' + object.get('price') + '</div>' + '</td>';

                    //Editar ---> Inicio
                    table += '<td>';
                    table += '<span class="btn_edit"> <a href="#" class="btn btn-link" row_id="' + object.id + '"> Editar</a> </span>';
                    //Só aparece quando o botão editar é clikado
                    table += '<span class="btn_save"> <a href="#" class="btn btn-link" row_id="' + object.id + '"> Salvar</a> | </span>';
                    table += '<span class="btn_cancel"> <a href="#" class="btn btn-link" row_id="' + object.id + '"> Cancelar</a> | </span>';
                    table += '<span class="btn_delete"> <a href="#" class="btn btn-link" row_id="' + object.id + '"> Deletar</a></span>';
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
                $('.btn_delete').hide();
                $('.btn_save_name').hide();
                $('.btn_cancel_name').hide();
                $('.btn-create').show();
            },
            error: function (error) {
                alert("Error: " + error.code + " " + error.message);
            }
        });
    },
    error: function (error) {
        alert("Error: " + error.code + " " + error.message);
    }
});

$(document).ready(function ($) {



    //Botão Editar <----> Inicio
    $(document).on('click', '.btn_edit', function (event) {

        event.preventDefault();
        var tbl_row = $(this).closest('tr');

        var row_id = tbl_row.attr('row_id');

        tbl_row.find('.btn_save').show();
        tbl_row.find('.btn_cancel').show();
        tbl_row.find('.btn_delete').show();

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

    //Botão Editar Nome Restaurante <----> Inicio
    $(document).on('click', '.btn_edit_name', function (event) {

        event.preventDefault();
        var name_title = $(this).closest('h2');

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
        tbl_row.find('.btn_delete').hide();

        tbl_row.find('.btn_edit').show();

        tbl_row.find('.row_data').attr('edit_type', 'click')
                .removeAttr('contenteditable').removeClass('bg-warning').css('padding', '');

        tbl_row.find('.row_data').each(function (index, val)
        {
            $(this).html($(this).attr('original_entry'));
        });


    });
    //Botão Cancelar <----> Fim

    //Botão Cancelar Nome Restaurante <----> Inicio
    $(document).on('click', '.btn_cancel_name', function (event) {
        event.preventDefault();

        var name_title = $(this).closest('h2');

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
        tbl_row.find('.btn_delete').hide();

        tbl_row.find('.btn_edit').show();


        //make the whole row editable
        tbl_row.find('.row_data')
                .attr('edit_type', 'click')
                .removeAttr('contenteditable')
                .removeClass('bg-warning')
                .css('padding', '');

        //--->get row data > start
        var Food = Parse.Object.extend("Food");
        var object = new Food();

        object.set("objectId", row_id);
        tbl_row.find('.row_data').each(function (index, val)
        {
            var col_val = $(this).text();
            if (index == 0) {
                object.set("foodname", col_val);
            }
            if (index == 1) {
                object.set("ingredients", col_val);
            }
            if (index == 2) {
                object.set("calories", col_val);
            }
            if (index == 3) {
                object.set("price", parseFloat(col_val));
            }
        });

        object.save();
        //--->get row data > end
    });
    //Botão Salvar <----> Fim

    //Botão Salvar Nome Restaurante <----> Inicio
    $(document).on('click', '.btn_save_name', function (event)
    {
        event.preventDefault();

        var name_title = $(this).closest('h2');

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
        name_title.find('.restaurant_name').each(function (index, val)
        {
            var col_val = $(this).text();

            //Salvando no banco
            restaurantObject.set("name", col_val);
            restaurantObject.save();

        });
        //--->get row data > end
    });
    //Botão Salvar Nome Restaurante <----> Fim

    //Botão Deletar <----> Inicio
    $(document).on('click', '.btn_delete', function (event) {
        event.preventDefault();

        var tbl_row = $(this).closest('tr');
        var row_id = tbl_row.attr('row_id');

        if (confirm("Você tem certeza que quer deletar esse item?")) {
            txt = "You pressed OK!";

            var Food = Parse.Object.extend("Food");
            var object = new Food();
            object.set("objectId", row_id);
            object.destroy();
            tbl_row.remove();


        }
    });
    //Botão Deletar <----> Fim
});

//Botão Adicionar <----> Inicio
function create() {

    var Food = Parse.Object.extend("Food");
    var object = new Food();
    object.set("fromRestaurant", restaurantObject);
    object.save(null, {
        success: function (food) {
            var row;
            row += '<tr row_id="' + food.id + '">';

            row += '<td><div class="row_data" edit_type="click" col_name="fname">' + food.get('foodname') + '</div>' + '</td>';
            row += '<td><div class="row_data" edit_type="click" col_name="fname">' + food.get('ingredients') + '</div>' + '</td>';
            row += '<td><div class="row_data" edit_type="click" col_name="fname">' + food.get('calories') + '</div>' + '</td>';
            row += '<td><div class="row_data" edit_type="click" col_name="fname">' + food.get('price') + '</div>' + '</td>';

            //Editar ---> Inicio
            row += '<td>';
            row += '<span class="btn_edit"> <a href="#" class="btn btn-link" row_id="' + food.id + '"> Editar</a> </span>';
            //Só aparece quando o botão editar é clikado
            row += '<span class="btn_save"> <a href="#" class="btn btn-link" row_id="' + food.id + '"> Salvar</a> | </span>';
            row += '<span class="btn_cancel"> <a href="#" class="btn btn-link" row_id="' + food.id + '"> Cancelar</a> | </span>';
            row += '<span class="btn_delete"> <a href="#" class="btn btn-link" row_id="' + food.id + '"> Deletar</a></span>';
            row += '</td>';
            //Editar ---> Fim
            row += '</tr>';

            $(document).find('table').append(row);
            $('.btn_save').hide();
            $('.btn_cancel').hide();
            $('.btn_delete').hide();


        },
        error: function (food, error) {
            // Execute any logic that should take place if the save fails.
            // error is a Parse.Error with an error code and message.
            alert('Failed to create new object, with error code: ' + error.message);
        }
    });
}
//Botão Adicionar <----> Fim

function logout() {
    Parse.User.logOut().then(() => {
        window.location = "index.html";
    });
}

