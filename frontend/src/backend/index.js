import {init} from './appFetch';
import * as userService from './userService';
import * as carteleraService from './carteleraService';
import * as shoppingService from './shoppingService';

export {default as NetworkError} from "./NetworkError";

// eslint-disable-next-line
export default {init, userService, carteleraService, shoppingService};
