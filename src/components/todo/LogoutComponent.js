import React, {Component} from 'react'

class LogoutComponent extends Component{
    render(){
        return(
            <div>
                <h1>You are logged out!</h1>
                <div className="container">
                    Thank you for using our application.
                    {sessionStorage.removeItem('authenticatedUser')}
                </div>
            </div>
        )
    }
}

export default LogoutComponent