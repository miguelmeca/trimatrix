package trimatrix.ui;

import java.io.Serializable;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.entities.ResultEntity;
import trimatrix.logic.EntityListLogic;
import trimatrix.logic.helper.Limit;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@CCGenClass (expressionBase="#{d.ResultsImportUI}")

public class ResultsImportUI extends MyWorkpageDispatchedBean implements Serializable {

    protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();

    public String getMappingImage() { return Constants.ACCEPT; }

    private Competitions competition;
    public String getCompDesc() { return competition!=null ? competition.toString() : null; }

    private String category;

    private Map<String, Limit> limitsMap;

    public ResultsImportUI(IWorkpageDispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * Call competition selection pop up
     * @param event
     */
    public void onCompetitionSearch(ActionEvent event) {
        IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.COMPETITION);
        entitySelectionUI.buildData(Entity.SCOUTCOMPETITIONS);

           entitySelectionUI.prepareCallback(new ISelectionCallback(){
            public void cancel() {
                m_popup.close();
            }
            public void selected(String id) {
                competition = (Competitions)ENTITYLISTLOGIC.get(Constants.Entity.COMPETITION, id);
                m_popup.close();
            }});
        m_popup = getWorkpage().createModalPopupInWorkpageContext();
        m_popup.setLeftTopReferenceCentered();
        m_popup.open(Constants.Page.COMPETITIONSELECTION.getUrl(), "Wettkampfsuche", 800, 600, this);
    }

    /**
     * F4 Help for categories depending on competition
     * @param event
     */
    public void onCategoryF4(ActionEvent event) {
        IdTextSelection idts = IdTextSelection.createInstance();
        for (String category : limitsMap.keySet()) {
            idts.addLine(category, "");
        }
        idts.setCallBack(new ISetId() {
            public void setId(String id) {
                category = id;
            }
        });
        idts.setWithHeader(false);
        idts.setSuppressHeadline(true);
        idts.setRenderTextColumn(false);
        idts.setPopupWidth(120);
        idts.setPopupHeight(100);
    }
}
