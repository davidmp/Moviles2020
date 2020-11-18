
class ModeloProductos{
  int id;
  String nombre;
  double precio;

  ModeloProductos({
  this.id, this.nombre, this.precio
  });

  Map<String, dynamic> toJson(){
    final Map<String, dynamic> data=new Map<String, dynamic>();
    data['id']=this.id;
    data['nombre']=this.nombre;
    data['precio']=this.precio;
    return data;
  }

  ModeloProductos.fromJson(Map<String, dynamic> json){
    id=json['id'];
    nombre=json['nombre'];
    precio=json['precio'];
  }

}