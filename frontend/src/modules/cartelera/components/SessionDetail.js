import React, {useEffect, useState} from "react";
import './styles/SessionDetail.css';

import {FormattedMessage, FormattedNumber,  FormattedDate, FormattedTime} from 'react-intl';
import {useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import * as actions from "../actions";
import {BackLink, RoleTypes} from "../../common";
import MovieLink from "../../common/components/MovieLink";
import FormCompra from "../../shopping/components/FormCompra";
import users from "../../users";
import {Errors} from '../../common';

export const SessionDetail = () => {
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const {id} = useParams();
    const dispatch = useDispatch();
    const sesion = useSelector(selectors.getSession);
    const [backendErrors, setBackendErrors] = useState(null);
    const sesionId = Number(id);
    const isRoleEspectador = useSelector(users.selectors.getUserRole) === RoleTypes.ESPECTADOR;



    useEffect( () => {
            const sesionId = Number(id);

            if(!Number.isNaN(id)){
                dispatch(actions.findSessionbyId(sesionId, errors => setBackendErrors(errors)))
            }
            return () => dispatch(actions.sessionByIdClear);
        },[id,dispatch]
    )
    if(backendErrors) {
        return(
            <div>
                <Errors errors={backendErrors}
                        onClose={() => setBackendErrors(null)}/>
            </div>
        )
    }

    if(!sesion){
        return null;
    }

    return(
        <div className = "sesions">
            <BackLink/>
            <div className = "sessions-header">
                <MovieLink nombrePelicula={sesion.nombrePelicula} id={sesion.idPelicula}></MovieLink>
                <hr></hr>
                <div className = "data-div">
                    <div>
                        <div className= "duracion_y_sala">
                            <h6 id="duracion"> <FormattedMessage id='project.global.fields.duration'/>
                                {' : '} {sesion.duracion} min</h6>
                            <p id= "Precios">
                                <FormattedMessage id='project.global.fields.price'/>{': '}
                                {/* eslint-disable-next-line */}
                                <FormattedNumber value={sesion.precio} style="currency" currency="EUR"/>
                            </p>
                        </div>
                    {/*sesion.fecha*/}
                        <div className= "nomSala_y_fecha" id = "nombre_sala_y_fecha">
                            <h6 id= "nombreSala">{sesion.nombreSala} </h6>
                            <h6 id= "fecha_y_hora">
                                <FormattedDate  value={new Date(sesion.fecha)}/>{' - '}
                                <FormattedTime  value={new Date(sesion.fecha)}/>
                            </h6>
                        </div>
                    </div>
                    <div id="localidadesLibres">
                        <br></br>
                        <br></br>
                        <p id= "localidadesLibres"><FormattedMessage id='project.global.fields.localidades'/>
                            {' : '}{sesion.localidadesLibres}</p>
                    </div>
                </div>
            </div>
            {loggedIn && isRoleEspectador &&
                <div>
                    <br/>
                    <FormCompra sesionId={sesionId}/>
                </div>
            }
        </div>
    );
}