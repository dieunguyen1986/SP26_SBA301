import React, { createContext, useEffect, useState } from "react";

export const AuthStatesContext = createContext();
export const AuthActionsContext = createContext();

const AuthProvider = ({ children }) => {
  // State
  const [userContext, setUserContext] = useState(null);
  const [language, setLanguage] = useState("EN");

  useEffect(() => {
    async function setData() {
      const user = localStorage.getItem("user");
      setUserContext(JSON.parse(user));
    }
    setData();
  }, []);

  // Actions/Functions

  const changeUser = (authResponse) => {
    const userInfo = { email: authResponse.email, fullName: authResponse.fullName, roles: authResponse.roles };

    // Decode token to get user info
    // const userInfo = jwt_decode(authResponse.accessToken);

    console.log("Set user to context");
    setUserContext(userInfo);
    localStorage.setItem("user", JSON.stringify(userInfo));

  };

  const changeLang = (other) => {
    setLanguage(other);
  };

  const logout = () => {
    localStorage.removeItem("user");
    setUserContext(null);

    window.location.href = "/login";
  };

  const stateValues = { userContext, language };
  const actionValues = { changeUser, logout, changeLang };

  return (
    <AuthStatesContext.Provider value={stateValues}>
      <AuthActionsContext.Provider value={actionValues}>
        {children}
      </AuthActionsContext.Provider>
    </AuthStatesContext.Provider>
  );
};

export default AuthProvider;
