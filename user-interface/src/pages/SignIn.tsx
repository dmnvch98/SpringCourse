import {useState} from "react";
import {signIn} from "../api/users";

export function SignIn() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    return (
        <form action="">
            <div>
                <button onClick={() => signIn(username,password)}>Sign In</button>
            </div>
            <div>
                <label htmlFor="username">Username</label>
                <input type="text" id="username" onChange={e => setUsername(e.target.value)}/>
            </div>

            <div>
                <label htmlFor="username">Password</label>
                <input type="password" id="password" onChange={e => setPassword(e.target.value)}/>
            </div>
        </form>
    )
}