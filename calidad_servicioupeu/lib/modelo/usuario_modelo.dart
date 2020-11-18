
import 'dart:convert';
import 'package:json_annotation/json_annotation.dart';

class ModeloRol {
  ModeloRol({
    this.authority,
  });

  String authority;

  factory ModeloRol.fromJson(Map<String, dynamic> json) => ModeloRol(
    authority: json["authority"],
  );

  Map<String, dynamic> toJson() {
   final Map<String, dynamic> data=new Map<String, dynamic>();
   data['authority']=this.authority;
   return data;
  }
}



class ModeloUsuario {
  ModeloUsuario({
    this.token,
    this.bearer,
    this.nombreUsuario,
    this.password,
    this.authorities,
  });

  String token;
  String bearer;
  String nombreUsuario;
  String password;
  List<ModeloRol> authorities;

  factory ModeloUsuario.fromJson(Map<String, dynamic> json) => ModeloUsuario(
    token: json["token"],
    bearer: json["bearer"],
    nombreUsuario: json["nombreUsuario"],
    password: json["password"],
    authorities: List<ModeloRol>.from(json["authorities"].map((x) => ModeloRol.fromJson(x))),
  );

  //"authorities": List<dynamic>.from(authorities.map((x) => x.toJson())),
  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data=new Map<String, dynamic>();
    data['token']=this.token;
    data['bearer']=this.bearer;
    data['nombreUsuario']=this.nombreUsuario;
    data['password']=this.password;
    data['authorities']=this.authorities;
    return data;
  }
}
