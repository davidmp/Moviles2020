




import 'package:calidad_servicioupeu/blocs/productos/productos_bloc.dart';
import 'package:calidad_servicioupeu/blocs/ticker/ticker_bloc.dart';
import 'package:calidad_servicioupeu/modelo/productos_modelo.dart';
import 'package:calidad_servicioupeu/repository/ProductosRepository.dart';
import 'package:calidad_servicioupeu/repository/ticker.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:animated_float_action_button/animated_floating_action_button.dart';
class TickerApp extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
      return MultiBlocProvider(
          providers: [
            BlocProvider(create: (_)=>TickerBloc(Ticker())),
            BlocProvider(create: (_)=>ProductosBloc( productosRepository: ProductosRepository())),
          ],
          child: MaterialApp(
            theme: ThemeData(primaryColor: Colors.lightBlue),
            home: TickerPage(),
          ));
  }

}


class TickerPage extends StatelessWidget{
final controllerNombre=new TextEditingController();
final controllerPrecio=new TextEditingController();
final GlobalKey<AnimatedFloatingActionButtonState> fabKey = GlobalKey();
  @override
  Widget build(BuildContext context) {
    BlocProvider.of<ProductosBloc>(context).add(ListarProductosEvent());
    return Scaffold(
      appBar: AppBar(
        title: BlocBuilder<TickerBloc, TickerState>(
          builder: (context, state){
            if(state is TickerTickSusccess){
              return Center(
                child: Text("Titulos Tick #${state.count}"),
              );
            }
            return const Center(
              child: Text(" Titulo (NA)"),
            );
          },
        ),
        actions: <Widget>[
          Padding(padding: EdgeInsets.only(right: 20.0),
          child: GestureDetector(
            onTap: (){
              print("Si funciona");
            },
            child: Icon(Icons.search, size: 26.0,),
          ),
          ),
          Padding(padding: EdgeInsets.only(right: 20.0),
          child: GestureDetector(
            onTap: (){
              final producto=new ModeloProductos();
              formDialog(context, producto);
              print("Si funciona 2");
              },
            child: Icon(Icons.add_box_sharp),
          ),
          )
        ],
      ),
      body: BlocBuilder<ProductosBloc, ProductosState>(
        builder: (context, state){
          if(state is ProductosLoadedState){
            /*return Center(
              child: Text("Tick #${state.productosList.length}"),
            );*/

            return ListView.builder(
                itemCount: state.productosList.length,
                itemBuilder: (context, index)=>
                 Card(
                  child: Container(
                  padding: EdgeInsets.all(10.0),
                    child: ListTile(
                   leading: Text(state.productosList[index].id.toString()),
                   title: Text(state.productosList[index].nombre),
                      trailing: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          IconButton(icon: Icon(Icons.edit), onPressed: (){
                          ModeloProductos productos=state.productosList[index];
                          controllerNombre.text=productos.nombre;
                          controllerPrecio.text=productos.precio.toString();
                          formDialog(context, productos);
                          }),
                          IconButton(icon: Icon(Icons.delete), onPressed: (){
                            showDialog(context: context,
                            barrierDismissible: true,
                              builder: (BuildContext context){
                              return AlertDialog(
                                title: Text("Mensaje de confirmacion"),
                                content: Text("Desea Eliminar?"),
                                actions: [
                                  FlatButton(child: const Text('CANCEL'),
                                    onPressed: (){
                                    Navigator.of(context).pop('Failure');
                                    },
                                  ),
                                  FlatButton( child: const Text('ACCEPT'),
                                      onPressed: (){
                                        Navigator.of(context).pop('Success');
                                      })
                                ],
                              );
                              }
                            ).then((value){
                              if(value.toString()=="Success"){
                                BlocProvider.of<ProductosBloc>(context).add(DeleteProductoEvent(producto: state.productosList[index]));
                              }
                            });




                          })
                        ],
                      ),
                 ),
                 ),
                ),
            );

          }
          return const Center(
            child: Text("Aqui va la numeracion de incremento"),
          );
        },
      ),

      bottomNavigationBar: BottomNavigationBar(
        selectedItemColor: Colors.white,
        unselectedItemColor: Colors.grey[400],
        backgroundColor: Theme.of(context).primaryColor,
        currentIndex: 0,
        items: [
          BottomNavigationBarItem(icon: new Icon(Icons.home), title: new Text("Home")),
          BottomNavigationBarItem(icon: new Icon(Icons.email), title: new Text("MSG")),
          BottomNavigationBarItem(icon: new Icon(Icons.person), title: new Text("Perfil")),
        ],
      ),

      floatingActionButton: AnimatedFloatingActionButton(
        key: fabKey,
        fabButtons: <Widget>[
          add(context),
          image(),
          inbox()
        ],
          colorStartAnimation: Colors.blue,
          colorEndAnimation: Colors.red,
        animatedIconData: AnimatedIcons.menu_close,
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.miniCenterFloat,
    );
  }


  Widget add(BuildContext context) {
    return FloatActionButtonText(
      onPressed: (){
        context.read<TickerBloc>().add(TickerStarted());
        context.read<ProductosBloc>().add(ListarProductosEvent());
        fabKey.currentState.animate();
      },
      icon: Icons.add,
      text: "Listar/Start Timer",
      textLeft: -150,
    );
  }

  Widget image() {
    return FloatActionButtonText(
      onPressed: (){
        fabKey.currentState.animate();
      },
      icon: Icons.image,
      textLeft: -150,
      text: "Visualizar Rota",
    );
  }

  Widget inbox() {
    return FloatActionButtonText(
      onPressed: (){
        fabKey.currentState.animate();
      },
      icon: Icons.inbox,
      textLeft: -135,
      text: "Desbloquear",
    );
  }


  Future formDialog(BuildContext context, ModeloProductos producto){
    return showDialog(context: context,
    barrierDismissible: false,
      builder: (BuildContext context){
      return AlertDialog(
        title: Text("Crear Producto"),
        content: Column(
          children: [
            TextField(
              obscureText: false,
              controller: controllerNombre,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                labelText: "Nombre:",
              ),
            ),
            TextField(
              obscureText: false,
              controller: controllerPrecio,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                labelText: "Precio:",
              ),
            )
          ],
        ),
        actions: <Widget>[
          FlatButton(child: Text('CANCEL'),
              onPressed: (){
            Navigator.of(context).pop('Cencel');
              }),
          FlatButton(child: Text('Guardar'),
              onPressed: (){
                producto.nombre=controllerNombre.value.text;
                producto.precio=double.parse(controllerPrecio.value.text.toString());
                controllerNombre.clear();
                controllerPrecio.clear();
                Navigator.of(context).pop(producto);
              })
        ],
      );
      }
    ).then((value){
      if(value.toString()!="Cencel" && value.toString()!=null){
        ModeloProductos data=value;
        print("VER: ${data.id}" );
        if(data.id==null){
          print("Datos: ${data.nombre}-${data.precio}");
          BlocProvider.of<ProductosBloc>(context).add(CreateProductoEvent(producto: data));
        }else{
          BlocProvider.of<ProductosBloc>(context).add(UpdateProductoEvent(producto: data));
        }
      }
    });
  }


}