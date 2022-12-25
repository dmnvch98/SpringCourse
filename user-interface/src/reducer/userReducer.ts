const SET_USER = "SET_USER"

const defaultState = {
    isAuth: false
}

export default function userReducer(state = defaultState, action: any) {
    switch (action.type) {
        case SET_USER:
            return {
                ...state,
                currentUser: action.payload,
                isAuth: true
            }
        default:
            return state;
    }
}

export const setUser = (user: any) => ({type: SET_USER, payload: user})