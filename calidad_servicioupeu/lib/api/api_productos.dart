import 'package:calidad_servicioupeu/modelo/usuario_modelo.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:calidad_servicioupeu/modelo/productos_modelo.dart';
import 'package:pretty_dio_logger/pretty_dio_logger.dart';
import 'package:retrofit/retrofit.dart';

import 'package:dio/dio.dart' hide Headers;
import 'dart:io';

import 'package:shared_preferences/shared_preferences.dart';

part 'api_productos.g.dart';

@RestApi(baseUrl: "http://172.22.90.1:8080")
abstract class ProductosApi{
  factory ProductosApi(Dio dio, {String baseUrl})=_ProductosApi;

  static ProductosApi create(){
    final dio=Dio();
    dio.interceptors.add(PrettyDioLogger());
    return ProductosApi(dio);
  }
  
  @GET("/producto/lista2")
  Future<List<ModeloProductos>> getProductos();

  @GET("/producto/lista2")
  Future<List<ModeloProductos>> getProductos2(@Header("Authorization") String token);

  @POST("/auth/login")
  Future<ModeloUsuario> login(@Body() ModeloUsuario usuario);

}



