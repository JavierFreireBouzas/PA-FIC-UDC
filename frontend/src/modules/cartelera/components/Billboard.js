import './styles/Billboard.css';
import {Movies} from './Movies';
import {useDispatch, useSelector} from "react-redux";
import * as actions from "../actions";
import * as selectors from '../selectors';
import DateSelector from "./DateSelector";
import {FormattedMessage} from "react-intl";

const Billboard = () => {
    const dispatch = useDispatch();
    const carteleraDate = useSelector(selectors.getCarteleraDate);

    return (
        <div className="billboard">
            <div className="billboard-header">
                <h1><FormattedMessage id="project.app.Home.Billboard.title"/></h1>
                <DateSelector id="billboardDate" className="custom-select my-1 mr-sm-2"
                              value={carteleraDate} onChange={e => dispatch(actions.getCartelera(e.target.value))}/>
            </div>
            <Movies/>
        </div>
    );

}

export default Billboard;