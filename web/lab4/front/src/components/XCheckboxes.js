import { useDispatch, useSelector } from 'react-redux';

import Checkbox from 'react-toolbox/lib/checkbox/Checkbox';
import { selectX, setX } from '../features/formHandler.js/formSlice';

function XCheckboxes() {
    const dispatch = useDispatch();
    const xCoordinate = useSelector(selectX);

    return (
        <div>
            <Checkbox label="-5" checked={xCoordinate === -5} onChange={() => dispatch(setX(-5))}/>
            <Checkbox label="-4" checked={xCoordinate === -4} onChange={() => dispatch(setX(-4))}/>
            <Checkbox label="-3" checked={xCoordinate === -3} onChange={() => dispatch(setX(-3))}/>
            <Checkbox label="-2" checked={xCoordinate === -2} onChange={() => dispatch(setX(-2))}/>
            <Checkbox label="-1" checked={xCoordinate === -1} onChange={() => dispatch(setX(-1))} />
            <Checkbox label="0" checked={xCoordinate === 0} onChange={() => dispatch(setX(0))} />
            <Checkbox label="1" checked={xCoordinate === 1} onChange={() => dispatch(setX(1))} />
            <Checkbox label="2" checked={xCoordinate === 2} onChange={() => dispatch(setX(2))} />
            <Checkbox label="3" checked={xCoordinate === 3} onChange={() => dispatch(setX(3))} />
        </div>
    );
}

export default XCheckboxes;