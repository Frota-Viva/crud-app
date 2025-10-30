<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String servletExclusao = request.getParameter("servletExclusao");
%>
<div class="modal-overlay"></div>
<section id="caixa-excluir" data-servlet=<%=servletExclusao%>>
    <img src="../../../assets/icons/atencao.svg" alt="Ícone de Atenção" id="atencao">
    <p>Você tem certeza que deseja excluir esses dados?</p>
    <div class="botoes-excluir-container">
        <button id="cancelar" class="botoes-excluir">Cancelar</button>
        <button id="efetuar" class="botoes-excluir">Efetuar</button>
    </div>
    <script src="../../../assets/JS/pop-up-excluir.js"></script>
</section>