CREATE ROLE estoque LOGIN PASSWORD 'estoque';
CREATE ROLE pagamento LOGIN PASSWORD 'pagamento';



CREATE DATABASE estoque;
CREATE DATABASE pagamento;


ALTER DATABASE pagamento OWNER TO pagamento;
ALTER DATABASE estoque OWNER TO estoque;

GRANT ALL ON DATABASE estoque TO "estoque";

GRANT ALL ON DATABASE pagamento TO "pagamento";