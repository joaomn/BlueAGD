import axios from "axios";
import { useState } from "react";

function Modal (props) {

    const titulo =
      props.tipoModal === "inserir" ? "Novo Cadastro" : "Alterar Cadastro";

    const [pessoa, setPessoa] = useState(props.pessoa);
    const [respostaAPI, setRespostaAPI] = useState("");

    const inserirDados = () => {
      axios
        .post("http://localhost:8080/cadastrar", {
          name: pessoa.name,
          email: pessoa.email,
          phone: pessoa.phone,
        })
        .then(() => {
          props.fecharModal();
          props.carregarDados();
        })
        .catch(() => setRespostaAPI("Erro: e-mail já cadastrado."));
    };

    const editarDados = (id) => {
      axios
        .put("http://localhost:8080/atualizar/" + id, {
          name: pessoa.name,
          email: pessoa.email,
          phone: pessoa.phone,
        })
        .then(() => {
          props.fecharModal();
          props.carregarDados();
        })
        .catch(() => setRespostaAPI("Erro: o campo telefone está inválido."));
    };

    return (
      <div className="modal">
        <div className="titulo">{titulo}</div>

        <div className="conteudo">
          <div className="input">
            <label htmlFor="name">Nome</label>
            <input
              type="text"
              name="name"
              defaultValue={pessoa.name}
              onChange={(e) => setPessoa({ ...pessoa, name: e.target.value })}
            />
          </div>

          <div className="input">
            <label htmlFor="email">E-mail</label>
            <input
              type="text"
              name="email"
              defaultValue={pessoa.email}
              onChange={(e) => setPessoa({ ...pessoa, email: e.target.value })}
              disabled={props.tipoModal === 'editar'}
            />
          </div>

          <div className="input">
            <label htmlFor="phone">Telefone</label>
            <input
              type="text"
              name="phone"
              defaultValue={pessoa.phone}
              onChange={(e) => setPessoa({ ...pessoa, phone: e.target.value })}
            />
          </div>
        </div>
        <div className="acao">
          <span>{respostaAPI}</span>
          <button className="save" onClick={() => {
            if(props.tipoModal === 'inserir') {
              inserirDados();
            } else if (props.tipoModal === 'editar') {
              editarDados(pessoa.email);
            }
          }}>
            SALVAR
          </button>
        </div>
      </div>
    );
}

export default Modal;