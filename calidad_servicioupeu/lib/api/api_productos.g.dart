
part of 'api_productos.dart';

class _ProductosApi implements ProductosApi{
  _ProductosApi(this._dio, {this.baseUrl}){
    ArgumentError.checkNotNull(_dio, '_dio');
    this.baseUrl ??="http://172.22.90.1:8080/producto";
  }

  final Dio _dio;
  String baseUrl;

  @override
  getProductos() async{

    const _extra=<String, Dynamic>{};
    final queryParameters= <String, Dynamic>{};
    final _data=<String, Dynamic>{};
    final Response<List<dynamic>> _result= await _dio.request('/lista',
        queryParameters:queryParameters,
        options:RequestOptions(
    method:'GET',
    headers:<String, Dynamic>{},
    extra:_extra,
    baseUrl:baseUrl
    ),
    data:_data);
    var value=_result.data
      .map((dynamic i)=>ModeloProductos.fromJson(i as Map<String, dynamic>)).toList();


    return Future.value(value);
  }


}
