




import 'package:calidad_servicioupeu/blocs/productos/productos_bloc.dart';
import 'package:calidad_servicioupeu/blocs/ticker/ticker_bloc.dart';
import 'package:calidad_servicioupeu/repository/ProductosRepository.dart';
import 'package:calidad_servicioupeu/repository/ticker.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

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
            onTap: (){  print("Si funciona 2"); },
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
                  /*  ListTile(
                  leading: Text(state.productosList[index].id.toString()),
                  title: Text(
                    state.productosList[index].nombre
                  ),
                )*/
                 Card(
                  child: Container(
                  padding: EdgeInsets.all(10.0),
                    child: ListTile(
                   leading: Text(state.productosList[index].id.toString()),
                   title: Text(state.productosList[index].nombre),
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
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          context.read<TickerBloc>().add(TickerStarted());
          context.read<ProductosBloc>().add(ListarProductosEvent());
        },
        tooltip: "Iniciar",
        child: const Icon(Icons.timer),
      ),
    );
  }

}