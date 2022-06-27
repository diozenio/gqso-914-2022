import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Soma implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String[] partes = exchange.getRequestURI().getPath().split("/");
            List<Double> numeros = new ArrayList<Double>();
            for (int i = 2; i < partes.length; i++) {
                boolean isNumeric = checkStr(partes[i]);
                if (isNumeric) {
                    double d = Double.parseDouble(partes[i]);
                    numeros.add(d);
                    System.out.println(numeros);
                } else {
                    byte[] resposta = "URL inválido".getBytes();
                    exchange.sendResponseHeaders(200, resposta.length);
                    exchange.getResponseBody().write(resposta);
                    return;
                }
            }
            double soma = numeros.stream().mapToDouble(Double::doubleValue).sum();
            byte[] resposta = Double.toString(soma).getBytes();
            exchange.sendResponseHeaders(200, resposta.length);
            exchange.getResponseBody().write(resposta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exchange.close();
        }
    }

    private boolean checkStr(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}