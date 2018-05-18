
${package}

import javax.jws.*;

@WebService()
public class ${name}{

    @WebMethod()
    public ${return} ${methodname}(${params}){
        ${body}
        return ${val}
    }
}