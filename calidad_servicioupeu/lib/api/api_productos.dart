import 'package:json_annotation/json_annotation.dart';
import 'package:calidad_servicioupeu/modelo/productos_modelo.dart';
import 'package:retrofit/retrofit.dart';
import 'package:dio/dio.dart' hide Headers;
import 'dart:io';

part 'api_productos.g.dart';

@RestApi(baseUrl: "http://172.22.90.1:8080/producto")
abstract class ProductosApi{
  factory ProductosApi(Dio dio, {String baseUrl})=_ProductosApi;

  @GET("/lista")
  Future<List<ModeloProductos>> getProductos();

}



