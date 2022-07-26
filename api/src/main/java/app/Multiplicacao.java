package app;

import io.jooby.annotations.*;
import io.jooby.exception.BadRequestException;

@Path("/multiplicacao/{num1}/{num2}")
public class Multiplicacao {

    @GET
    public double calculaMultiplicacao(@PathParam String num1, @PathParam String num2) {
        try {
          return Float.parseFloat(num1)*Float.parseFloat(num2);
        } catch (NumberFormatException nfe) {
          throw new BadRequestException("Parâmetros inválidos: " + num1 + "" + num2);
        }
    }
}
