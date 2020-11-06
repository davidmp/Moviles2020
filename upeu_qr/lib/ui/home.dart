import 'dart:async';
import 'package:flutter/material.dart';
import 'package:barcode_scan/barcode_scan.dart';
import 'package:flutter/services.dart';

class Home extends StatefulWidget{
  @override
  HomeState createState(){
    return new HomeState();
  }
}

class HomeState extends State<Home>{
  String resultado="Contenido de QR leer ";

  Future scanQR() async{
    try{
      String qrResult= await BarcodeScanner.scan();
      setState(() {
        resultado=qrResult;
      });
    } on PlatformException catch(ex){
      if(ex.code==BarcodeScanner.CameraAccessDenied){
        setState(() {
          resultado="Tiene permiso denegado";
        });
      }else{
        setState(() {
          resultado="Tiene un error desconocido $ex";
        });
      }
    } on FormatException{
      setState(() {
        resultado="Intentar Nuevamente";
      });
    }catch(e){
      resultado="Error desconocido $e";
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("UpeU Qr"),
        centerTitle: true,
      ),
      body: Center(
        child: Text(resultado),
      ),
      floatingActionButton: FloatingActionButton.extended(
          onPressed: scanQR,
          backgroundColor: Colors.teal,
          icon: Icon(Icons.camera_alt_outlined),
          label: Text("QR scan")
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }

}