import axios from "axios";
import { useState, useEffect } from "react";
import Popup from "reactjs-popup";
import Modal from "./Modal";

function Agenda() {
  
  const carregarDados = () => {
    axios.get("http://localhost:8080/listar").then((res) => setDados(res.data));
  };

  const excluirDados = (id) => {
    axios
      .delete("http://localhost:8080/deletar/" + id)
      .then(() => carregarDados());
  };

  const [dados, setDados] = useState([]);
  const [modal, setModal] = useState(false);
  const [tipoModal, setTipoModal] = useState("");
  const [pessoa, setPessoa] = useState({});

  useEffect(() => {
    carregarDados();
  }, []);

  const handleClick = (tipoModal, pessoa) => {
    setModal(true);
    setTipoModal(tipoModal);
    setPessoa(pessoa);
  }

  const fecharModal = () => setModal(false);

  return (
    <>
      <div className={modal ? "fundo-modal" : undefined}>
        <Popup open={modal} onClose={() => setModal(false)}>
          <Modal
            tipoModal={tipoModal}
            pessoa={pessoa}
            fecharModal={fecharModal}
            carregarDados={carregarDados}
          />
        </Popup>
      </div>

      <div className="table">
        <table>
          <thead>
            <tr>
              <th>Nome</th>
              <th>E-mail</th>
              <th>Telefone</th>
              <th className="titulo-botoes">
                <button
                  className="insert"
                  onClick={() =>
                    handleClick("inserir", { name: "", email: "", phone: "" })
                  }
                >
                  INSERIR
                </button>
              </th>
            </tr>
          </thead>
          <tbody>
            {dados.map((e) => {
              return (
                <tr key={e.email}>
                  <td>{e.name}</td>
                  <td>{e.email}</td>
                  <td>{e.phone}</td>
                  <td className="botoes">
                    <button
                      className="edit"
                      onClick={() =>
                        handleClick("editar", {
                          name: e.name,
                          email: e.email,
                          phone: e.phone,
                        })
                      }
                    >
                      EDITAR
                    </button>
                    <button
                      className="delete"
                      onClick={() => excluirDados(e.email)}
                    >
                      EXCLUIR
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default Agenda;
