import {useSelector} from 'react-redux';
import {Route, Routes} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import users, {ChangePassword, Login, Logout, SignUp, UpdateProfile} from '../../users';
import {MovieDetail} from "../../cartelera/components/MovieDetail";
import {SessionDetail} from "../../cartelera/components/SessionDetail";
import {DeliverTickets} from "../../shopping/components/DeliverTickets";
import {FormCompra, CompraCompleted, FindCompras, FindComprasResult} from '../../shopping';
import {RoleTypes} from "../../common";

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const isRoleEspectador = useSelector(users.selectors.getUserRole) === RoleTypes.ESPECTADOR;
    const isRoleTaquillero = useSelector(users.selectors.getUserRole) === RoleTypes.TAQUILLERO;
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Routes>
                <Route path="/*" element={<Home/>}/>
                <Route path="/cartelera/peliculas/:id" element={<MovieDetail/>}/>
                <Route path="/cartelera/sesiones/:id" element={<SessionDetail/>}/>
                {loggedIn && <Route path="/users/update-profile" element={<UpdateProfile/>}/>}
                {loggedIn && <Route path="/users/change-password" element={<ChangePassword/>}/>}
                {loggedIn && <Route path="/users/logout" element={<Logout/>}/>}
                {loggedIn && isRoleEspectador && <Route path="/shopping/comprar" element={<FormCompra/>}/>}
                {loggedIn && isRoleEspectador && <Route path="/shopping/find-compras" element={<FindCompras/>}/>}
                {loggedIn && isRoleEspectador && <Route path="/shopping/find-compras-result" element={<FindComprasResult/>}/>}
                {loggedIn && isRoleEspectador && <Route path="/shopping/comprar-completed" element={<CompraCompleted/>}/>}
                {loggedIn && isRoleTaquillero && <Route path="/shopping/entregar-entradas" element={<DeliverTickets/>}/>}
                {!loggedIn && <Route path="/users/login" element={<Login/>}/>}
                {!loggedIn && <Route path="/users/signup" element={<SignUp/>}/>}
            </Routes>
        </div>

    );

};

export default Body;
