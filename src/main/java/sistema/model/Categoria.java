/**
 * Faculdade Anhambi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Enumeração das categorias de eventos disponíveis no sistema
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema.model;

public enum Categoria {
    FESTA("Festa"),
    EVENTO_ESPORTIVO("Evento Esportivo"),
    SHOW("Show"),
    CULTURAL("Evento Cultural"),
    WORKSHOP("Workshop"),
    PALESTRA("Palestra");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
