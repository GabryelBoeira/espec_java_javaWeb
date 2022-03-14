<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>CRUD Cidades</title>

    <!-- CSS only -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark">
        <span class="navbar-brand text-white">${Session.usuarioAtual}</span>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="/logout" class="nav-link btn btn-secondary">Sair da aplicação</a>
            </li>
        </ul>
    </nav>

    <div class="container-fluid">

        <div class="jumbotron mt-5">
            <h1>GERENCIAMENTO DE CIDADES</h1>
            <p>UM CRUD PARA CRIAR, ALTERAR, EXCLUIR E LISTAR CIDADES</p>
        </div>
        <#if cidadeAtual??>

            <form action="/alterar" method="POST" class="needs-validation" novalidate>
            <input type="hidden" name="nomeAtual" value="${(cidadeAtual.nome)!}" />
            <input type="hidden" name="estadoAtual" value="${(cidadeAtual.estado)!}" />
        <#else>

            <form action="/criar" method="POST" class="needs-validation" novalidate>
        </#if>
            <div class="form-group">
                <label for="nome">Cidade:</label>
                <input required value="${(cidadeAtual.nome)!}${nomeInformado!}" type="text" class="form-control ${(nome??)?then('is-invalid', '')}" placeholder="Informe o nome da cidade" id="nome" name="nome" />

                <div class="invalid-feedback" > ${nome!} </div>
            </div>
            <div class="form-group">
                <label for="estado">Estado:</label>
                <input required value="${(cidadeAtual.estado)!}${estadoInformado!}" type="text" class="form-control ${(estado??)?then('is-invalid', '')}" placeholder="Informe o estado ao qual a cidade pertence" id="estado" name="estado" />

                <div class="invalid-feedback" > ${estado!} </div>
            </div>
        
        <#if cidadeAtual??>
            <button type="submit" class="btn btn-warning">CONCLUIR ALTERACAO</button>
        <#else>
            <button type="submit" class="btn btn-primary">CRIAR</button>
        </#if>

        </form>
        <table class="table table-striped table-hover mt-5">
            <thead class="thead-dark">
                <tr>
                    <th>Nome</th>
                    <th>Estado</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <#list cidadeList as cidade>
                    <tr>
                        <td>${cidade.nome}</td>
                        <td>${cidade.estado}</td>
                        <td>
                            <div class="d-flex d-justify-content-center">
                                <a href="/prepararAlterar?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-warning mr-3">ALTERAR</a>
                                <a href="/excluir?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-warning mr-3">EXCLUIR</a>
                            </div>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</body>
</html>