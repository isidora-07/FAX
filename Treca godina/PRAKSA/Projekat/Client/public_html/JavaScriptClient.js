
class Artist{
  constructor(id, name, date, nameBend){
    this.id = id;
    this.name = name;
    this.date = date;
    this.nameBend = nameBend;
  }

  present(){
    return this.id + " " + this.name + " " + this.date + " " + this.nameBend;
  }
}

class Genre{
  constructor(id, name){
    this.id = id;
    this.name = name;
  }

  present(){
    return this.id + " " + this.name;
  }
}

class Song{
  constructor(id, name, nameBand){
    this.id = id;
    this.name = name;
    this.nameBand = nameBand;
  }

  present(){
    return this.id + " " + this.name + " " + this.nameBand;
  }
}

class Bend{
  constructor(id, name, nameGenre){
    this.id = id;
    this.name = name;
    this.nameGenre = nameGenre;
  }

  present(){
    return this.id + " " + this.name + " " + this.nameGenre;
  }
}

var _token;
var username;
var password;
var _url = "https://localhost:44389/api/";
var artists = [];
var bends = [];
var genres = [];
var artists_bend = [];
var songs = [];

$(document).ready(function(){

    $("#login").click(function(){
      username = document.getElementById("username").value;
      password = document.getElementById("password").value;

        $.loginPost(_url + "login", 
          {
              "Username": username,
              "Password": password
          });
    })
});

loginSucces = function(data, textStatus, jqXHR){
  var moj_json = JSON.stringify(data);
  var response = JSON.parse(moj_json);

  sessionStorage.setItem("token",response.token);
  window.location.href="main.html";
}

$.loginPost = function(url, data) {
    return jQuery.ajax({
        'type': 'POST',
        'url': url,
        'contentType': 'application/json',
        'data': JSON.stringify(data),
        'success': loginSucces,
        'statusCode':{
          401:function(){
            alert("Uneli ste pogrešne podatke");
          }
        },
        'dataType': 'json'
    });
};

MakeArtists = function(data, textStatus, jqXHR){

  artists = [];
  data.forEach(element => {
    var moj_json = JSON.stringify(element);
    var response = JSON.parse(moj_json);
    var artist = new Artist(response.idArtist, response.nameArtist, response.dateArtist, response.nameBend);
    artists.push(artist);
  });

  $.getBends(_url + "bend");
}

MakeArtistsByBend = function(data, textStatus, jqXHR){

  artists_bend = [];

  data.forEach(element => {
    var moj_json = JSON.stringify(element);
    var response = JSON.parse(moj_json);
    var artist = new Artist(response.idArtist, response.nameArtist, response.dateArtist, response.nameBend);
    artists_bend.push(artist);
  });

  $.FillTableArtistsByBend();
}

$.getGenres = function(url){
  return jQuery.ajax({
    'type': 'GET',
    'url': url,
    'success': MakeGenre,
    'statusCode':{
      401:function(){
        alert("Greska kod autorizacije");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token")
    }
  });
}

MakeGenre = function(data, textStatus, jqXHR){
  genres = [];
  data.forEach(element => {
    var moj_json = JSON.stringify(element);
    var response = JSON.parse(moj_json);
    var genre = new Genre(response.idGenre, response.nameGenre);
    genres.push(genre);
  });


  $.FillSelectGenre();
  $.FillTableBends();
  $.FillTableArtists();
  $.FillTableGenres();
  $.FillTableSongs();
}

$.FillSelectGenre = function(){
  var genre_select = document.getElementById("genres_select");
  var innerString = "";
  genres.forEach(element => {
    innerString += "<option value='" + element.id + "'>" + element.name + "</option>";
  });
  genre_select.innerHTML = innerString;
}

$.getSongs = function(url){
  return jQuery.ajax({
    'type': 'GET',
    'url': url,
    'success': MakeSongs,
    'statusCode':{
      401:function(){
        alert("Greska kod autorizacije");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token")
    }
  });
}

MakeSongs = function(data, textStatus, jqXHR){
  songs = [];
  data.forEach(element => {
    var moj_json = JSON.stringify(element);
    var response = JSON.parse(moj_json);
    var song = new Song(response.idSong, response.nameSong, response.nameBand);
    songs.push(song);
  });

  $.getGenres(_url + "genre");
}

MakeBends = function(data, textStatus, jqXHR){

  bends = [];
  data.forEach(element => {
    var moj_json = JSON.stringify(element);
    var response = JSON.parse(moj_json);
    var bend = new Bend(response.idBand, response.nameBend, response.nameGenre);
    bends.push(bend);
  });

  $.getSongs(_url + "song");
}

function getArtistsByBendClick(index){

  if(index == -1){
    $.getArtists(_url + "artist");

  }else{
    var url = _url + "bend/getArtistByIdBend?idBend=" + bends[index]["id"];

    $.getArtistsByIdBend(url);
  }
}

$.FillTableBends = function(){
  var table = document.getElementById("table_bend");
  table.innerHTML="<tr onclick='getArtistsByBendClick(-1)'><th>Naziv benda </th><th>Žanr</th><th></th></tr>";

  var innerString = "";

  for(var i=0; i<bends.length; i++){
    innerString += "<tr>";

    innerString +="<td onclick='getArtistsByBendClick(" + i + ")'>" + bends[i]["name"] + "</td>";
    innerString +="<td onclick='getArtistsByBendClick(" + i + ")'>" + bends[i]["nameGenre"] + "</td>";
    innerString +="<td><input type='button' value='Izbrisi' onClick='deleteBendClick(" + bends[i]["id"] + ")'></td>";

    innerString += "</tr>";
  }

  table.innerHTML += innerString;
}

deleteBendClick = function(id){
  $.deleteBend(_url + "bend/deleteBend?id=" + id);
}

succesDeleteBend = function(data, textStatus, jqXHR){
  alert("Uspešno ste obrisali.");
  $.getArtists(_url + "artist");
}

$.deleteBend = function(url){
  return jQuery.ajax({
    type: "DELETE",
    url: url,
    success: succesDeleteBend,
    statusCode:{
      401:function(){
        alert("Greska kod autorizacije");
      },
      400:function(){
        alert("Los Request");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token")
    }
  });
}

$.FillTableSongs = function(){
  var table = document.getElementById("table_song");
  table.innerHTML="<tr><th>Naziv pesme</th><th> Naziv benda</th></tr>";

  var innerString = "";

  for(var i=0; i<songs.length; i++){
    innerString += "<tr>";

    innerString +="<td>" + songs[i]["name"] + "</td>";
    innerString +="<td>" + songs[i]["nameBand"] + "</td>";

    innerString += "</tr>";
  }

  table.innerHTML += innerString;
}

$.FillTableArtists = function(){
  var table = document.getElementById("table_artist");
  table.innerHTML = "<tr><th>Ime muzičara</th><th>Datum rođenja</th><th>Naziv benda</th></tr>";

  var innerString = "";

  artists.forEach(artist => {
    innerString += "<tr>";

    innerString +="<td>" + artist["name"] + "</td>";
    innerString +="<td>" + artist["date"] + "</td>";
    innerString +="<td>" + artist["nameBend"] + "</td>";

    innerString += "</tr>";
  });

  table.innerHTML += innerString;
}

$.FillTableArtistsByBend = function(){
  var table = document.getElementById("table_artist");

  table.innerHTML = "<tr><th>Ime muzičara</th><th> Datum rođenja</th><th>Naziv benda</th></tr>";
  var innerString = "";

  artists_bend.forEach(artist => {
    innerString += "<tr>";

    innerString +="<td>" + artist["name"] + "</td>";
    innerString +="<td>" + artist["date"] + "</td>";
    innerString +="<td>" + artist["nameBend"] + "</td>";

    innerString += "</tr>";
  });

  table.innerHTML += innerString;
}

$.FillTableGenres = function(){
  var table = document.getElementById("table_genre");
  table.innerHTML = "<tr><th>Naziv žanra</th></tr>";

  var innerString = "";

  genres.forEach(genre => {
    innerString += "<tr>";

    innerString +="<td>" + genre["name"] + "</td>";

    innerString += "</tr>";
  });

  table.innerHTML += innerString;
}

$.getArtists = function(url){
  return jQuery.ajax({
    'type': 'GET',
    'url': url,
    'success': MakeArtists,
    'statusCode':{
      401:function(){
        alert("Greska kod autorizacije");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token")
    }
  });
}

$.getBends = function(url){
  return jQuery.ajax({
    'type': 'GET',
    'url': url,
    'success': MakeBends,
    'statusCode':{
      401:function(){
        alert("Greska kod autorizacije");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token")
    }
  });
}

$.getArtistsByIdBend = function(url){
  return jQuery.ajax({
    'type': 'GET',
    'url': url,
    'success': MakeArtistsByBend,
    'statusCode':{
      401:function(){
        alert("Greska kod autorizacije");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token")
    }
  });
}

function _getContent(){
  $.getArtists(_url + "artist");
}

//MakeArtists -> MakeBends -> FillTableBends

function input(){
  var e = document.getElementById("genres_select");
  var idGenre = e.options[e.selectedIndex].value;
  var nazivBenda = document.getElementById("naziv_benda_input").value;

  if(nazivBenda != ""){
    $.insertBend(_url + "bend", nazivBenda, idGenre);
  }else{
    window.alert("Niste uneli naziv benda");
  }
}

succesInputBend = function(data, textStatus, jqXHR){
  alert("Uspesno");
}

$.insertBend = function(url, nazivBenda, idGenre){

  var _data = JSON.stringify({
    "nameBend": nazivBenda,
    "idGenre": idGenre
  });

  return jQuery.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    data: _data,
    dataType: "json",
    url: url,
    success: succesInputBend,
    statusCode:{
      401:function(){
        alert("Greska kod autorizacije");
      },
      400:function(){
        alert("Los Request");
      }
    },
    headers:{
      "Authorization": "Bearer " + sessionStorage.getItem("token"),
      "Content-Length": "<calculated when request is sent>",
      "Host": "<calculated when request is sent>",
      "User-Agent": "PostmanRuntime/7.26.2",
      "Accept": "*/*",
      "Accept-Encoding": "gzip, deflate, br",
      "Connection": "keep-alive",
      "Content-Type": "application/json"
    }
  });
}