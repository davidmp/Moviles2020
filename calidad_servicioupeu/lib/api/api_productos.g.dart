
part of 'api_productos.dart';

class _ProductosApi implements ProductosApi{
  _ProductosApi(this._dio, {this.baseUrl}){
    ArgumentError.checkNotNull(_dio, '_dio');
    this.baseUrl ??="http://172.22.90.1:8080";
  }

  final Dio _dio;
  String baseUrl;

  @override
  getProductos() async{
    final prefs= await SharedPreferences.getInstance();
    var tokenx=prefs.getString("token");
    print("VER: ${tokenx}");
    ArgumentError.checkNotNull(tokenx, "token");
    const _extra=<String, dynamic>{};
    final queryParameters= <String, dynamic>{};
    final _data=<String, dynamic>{};
    final Response<List<dynamic>> _result= await _dio.request('/producto/lista2',
        queryParameters:queryParameters,
        options:RequestOptions(
    method:'GET',
    headers:<String, dynamic>{"Authorization":tokenx},
    extra:_extra,
    baseUrl:baseUrl
    ),
    data:_data);
    var value=_result.data
      .map((dynamic i)=>ModeloProductos.fromJson(i as Map<String, dynamic>)).toList();


    return Future.value(value);
  }

  @override
  getProductos2(token) async{
  final prefs= await SharedPreferences.getInstance();
   var tokenx=prefs.getString("token");
   print("TOKEN es: $tokenx");
   ArgumentError.checkNotNull(token, "token");
    const _extra=<String, dynamic>{};
    final queryParameters= <String, dynamic>{};
    final _data=<String, dynamic>{};
    final Response<List<dynamic>> _result= await _dio.request('/producto/lista2',
        queryParameters:queryParameters,
        options:RequestOptions(
            method:'GET',
            headers:<String, dynamic>{"Authorization":token},
            extra:_extra,
            baseUrl:baseUrl
        ),
        data:_data);
    var value=_result.data
        .map((dynamic i)=>ModeloProductos.fromJson(i as Map<String, dynamic>)).toList();


    return Future.value(value);
  }

  @override
  login(user) async{
    ArgumentError.checkNotNull(user, "user");
    const _extra=<String, dynamic>{};
    final queryParameters= <String, dynamic>{};
    final _data=<String, dynamic>{};
    _data.addAll(user.toJson()?? <String, dynamic>{});
    final Response<Map<String,dynamic>> _result= await _dio.request('/auth/login',
        queryParameters:queryParameters,
        options:RequestOptions(
            method:'POST',
            headers:<String, dynamic>{},
            extra:_extra,
            baseUrl:baseUrl
        ),
        data:_data);
    var value=ModeloUsuario.fromJson(_result.data);
    return Future.value(value);
  }

  @override
  getProductoId(id) async{
    ArgumentError.checkNotNull(id, 'id');
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '/producto/detail/$id',
        queryParameters: queryParameters,
        options: RequestOptions(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
            baseUrl: baseUrl),
        data: _data);
    final value = ModeloProductos.fromJson(_result.data);
    return Future.value(value);
  }

  @override
  deleteProducto(id) async{
    ArgumentError.checkNotNull(id, '0');
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '/producto/delete/$id',
        queryParameters: queryParameters,
        options: RequestOptions(
            method: 'DELETE',
            headers: <String, dynamic>{},
            extra: _extra,
            baseUrl: baseUrl),
        data: _data);
    final value = ModeloMensaje.fromJson(_result.data);
    return Future.value(value);
  }

  @override
  updateProducto(id, producto) async{
    ArgumentError.checkNotNull(id, '0');
    ArgumentError.checkNotNull(producto, 'producto');
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _data = <String, dynamic>{};
    _data.addAll(producto.toJson() ?? <String, dynamic>{});
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '/producto/update/$id',
        queryParameters: queryParameters,
        options: RequestOptions(
            method: 'PUT',
            headers: <String, dynamic>{},
            extra: _extra,
            baseUrl: baseUrl),
        data: _data);
    final value = ModeloMensaje.fromJson(_result.data);
    return Future.value(value);
  }

  @override
  createProducto(producto) async{
    ArgumentError.checkNotNull(producto, 'producto');
    final prefs= await SharedPreferences.getInstance();
    var tokenx=prefs.getString("token");
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _data = <String, dynamic>{};
    _data.addAll(producto.toJson() ?? <String, dynamic>{});
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '/producto/create',
        queryParameters: queryParameters,
        options: RequestOptions(
            method: 'POST',
            headers: <String, dynamic>{"Authorization":tokenx},
            extra: _extra,
            baseUrl: baseUrl),
        data: _data);
    final value = ModeloMensaje.fromJson(_result.data);
    return Future.value(value);
  }

}
