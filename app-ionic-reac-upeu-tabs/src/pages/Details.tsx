import React, {useState, useEffect} from 'react'
import{IonBackButton, IonButtons, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonPage, IonTabBar, IonTitle, IonToolbar} from '@ionic/react'
import {RouteComponentProps} from 'react-router'
import { Console } from 'console';
const Details: React.FunctionComponent=(props)=>{
    let prop:any=props;
    let match:any=prop.match;
    let datax:any=match.params.data;
    const obj=JSON.parse(datax);
    
    //alert(obj.nombre);

    return (
        <IonPage>
            <IonHeader>
                <IonToolbar>
                <IonButtons slot="start">
                <IonBackButton defaultHref="/tab2" />
                </IonButtons>
                <IonTitle>Detalle Productos</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent fullscreen>
                <IonItem>
                    <IonLabel position="fixed">ID:</IonLabel>
                    <IonInput value={obj.id}></IonInput>
                </IonItem>
                <IonItem>
                    <IonLabel position="fixed">Nombre:</IonLabel>
                    <IonInput value={obj.nombre}></IonInput>
                </IonItem>
                <IonItem>
                    <IonLabel position="fixed">Precio:</IonLabel>
                    <IonInput value={obj.precio}></IonInput>
                </IonItem>                
            </IonContent>
        </IonPage>
    );

};

export default Details;