import React from 'react';
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonList, IonItem, IonButton } from '@ionic/react';
import ExploreContainer from '../components/ExploreContainer';
import './Tab2.css';
import axios from 'axios';
const URL_API="http://ec2-3-238-229-138.compute-1.amazonaws.com:8080/CalidadServApi-0.0.1-SNAPSHOT/producto/lista";

const fetchProducts=()=>{
  return axios({
    url:URL_API,
    method:'get'
  }).then(response=>{
    console.log(response);
    return response.data;
  })
}


const Tab2: React.FC = (props) => {

  const [productos, setProductos]=React.useState([]);
  const items:any[]=[];

  React.useEffect(()=>{
    fetchProducts().then(data=>setProductos(data));
  }, []);

  const mostrarDetalle=(data:any)=>{
    let prop:any =props;
    prop.history.push({
      pathname:'/details/'+JSON.stringify(data),

    })
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Tab 2</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonList color="primary">
          {
            productos.map(prod=>{
              return(
                <IonItem>
                  {prod['nombre']}
                  <IonButton onClick={()=>{mostrarDetalle(prod)}} slot="end" >Detalle</IonButton>
                </IonItem>
              );
            })
          }
        </IonList>
      </IonContent>
    </IonPage>
  );
};

export default Tab2;
