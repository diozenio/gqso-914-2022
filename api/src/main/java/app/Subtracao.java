package app;

import io.jooby.annotations.*;
import io.jooby.exception.BadRequestException;

@Path("/subtracao/{num1}/{num2}")
public class Subtracao {

    @GET
    public double calculaSubtracao(@PathParam String num1, @PathParam String num2) {
        try {
          return Float.parseFloat(num1)-Float.parseFloat(num2);
        } catch (NumberFormatException nfe) {
          throw new BadRequestException("Parâmetros inválidos: " + num1 + "" + num2);
        }
    }
}
