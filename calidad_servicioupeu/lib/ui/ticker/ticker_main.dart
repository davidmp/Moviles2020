



import 'package:calidad_servicioupeu/blocs/ticker/ticker_bloc.dart';
import 'package:calidad_servicioupeu/repository/ticker.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class TickerApp extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
      return MultiBlocProvider(
          providers: [
            BlocProvider(create: (_)=>TickerBloc(Ticker())),

          ],
          child: TickerPage());
  }

}


class TickerPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(child: Text("Contador Page"),),
      ),
      body: BlocBuilder<TickerBloc, TickerState>(
        builder: (context, state){
          if(state is TickerTickSusccess){
            return Center(
              child: Text("Tick #${state.count}"),
            );
          }
          return const Center(
            child: Text("Aqui va la numeracion de incremento"),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: ()=>context.read<TickerBloc>().add(TickerStarted()),
        tooltip: "Iniciar",
        child: const Icon(Icons.timer),
      ),
    );
  }

}