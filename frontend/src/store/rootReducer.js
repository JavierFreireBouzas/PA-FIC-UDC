import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import cartelera from '../modules/cartelera';
import shopping from '../modules/shopping';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    cartelera: cartelera.reducer,
    shopping: shopping.reducer,
});

export default rootReducer;
