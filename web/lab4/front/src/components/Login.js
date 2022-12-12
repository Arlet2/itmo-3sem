import React from "react";

import Card from "react-toolbox/lib/card/Card";
import CardTitle from "react-toolbox/lib/card/CardTitle";
import CardActions from "react-toolbox/lib/card/CardActions";
import CardText from "react-toolbox/lib/card/CardText";

function Login() {
    return (
        <div>
            <Card>
                <CardTitle title="Authorization"/>

                <CardText>
                    <input type="button"/>
                </CardText>
            </Card>
        </div>
    );
}

export default Login;