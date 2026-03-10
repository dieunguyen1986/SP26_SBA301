import {
  AuthActionsContext,
  AuthStatesContext,
} from "@/app/provider/AuthProvider";
import { useContext } from "react";

const useAuth = () => {
  const stateContext = useContext(AuthStatesContext);
  const actionContext = useContext(AuthActionsContext);

  console.log("useAuth - stateContext:", stateContext);
  console.log("useAuth - actionContext:", actionContext);

  if (!stateContext || !actionContext) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return { ...stateContext, ...actionContext };

  // useContext(AuthStatesContext) -> { userContext, language, changeUser, logout, changeLang }
};

export default useAuth;
