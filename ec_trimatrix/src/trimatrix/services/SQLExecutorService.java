package trimatrix.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import trimatrix.db.DAOLayer;
import trimatrix.db.Results;
import trimatrix.db.Schedules;
import trimatrix.db.ZonesSwim;
import trimatrix.entities.AttachmentEntity;
import trimatrix.entities.CompetitionEntity;
import trimatrix.entities.DoctorEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.ResultEntity;
import trimatrix.entities.ScheduleEntity;
import trimatrix.entities.TestEntity;
import trimatrix.entities.UserEntity;
import trimatrix.logic.LogicLayer;
import trimatrix.logic.helper.Limit;
import trimatrix.relations.IRelationData;
import trimatrix.relations.PersonAttachmentRelation;
import trimatrix.relations.PersonDoctorRelation;
import trimatrix.relations.PersonPersonRelation;
import trimatrix.structures.SFunctionTree;
import trimatrix.structures.SValueList;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Helper;
import trimatrix.utils.SearchRange;
import trimatrix.utils.Constants.Role;

/**
 * @author Meex
 *
 */
public class SQLExecutorService {
    public static final Log logger = LogFactory.getLog(SQLExecutorService.class);

    public static final String ID = "ID";

    public static final String USERENTITYLISTQUERY = "UserEntityList";
    public static final String PERSONENTITYLISTQUERY = "PersonEntityList";
    public static final String PERSONBYROLEENTITYLISTQUERY = "PersonByRoleEntityList";
    public static final String DOCTORENTITYLISTQUERY = "DoctorEntityList";
    public static final String ATHLETESENTITYLISTQUERY = "AthletesEntityList";
    public static final String ATTACHMENTENTITYLISTQUERY = "AttachmentEntityList";
    public static final String TESTENTITYLISTQUERY = "TestEntityList";
    public static final String COMPETITIONENTITYLISTQUERY = "CompetitionEntityList";
    public static final String SCHEDULEENTITYLISTQUERY = "ScheduleEntityList";
    public static final String FUNCTIONTREEQUERY = "FunctionTree";
    public static final String LANGUAGEVALUELISTQUERY = "LanguageValueList";
    public static final String SALUTATIONVALUELISTQUERY = "SalutationValueList";
    public static final String LOGONLANGUAGEVALUELISTQUERY = "LogonLanguageValueList";
    public static final String PERSONRELATIONENTITYQUERY = "PersonRelationEntityList";
    public static final String DOCTORRELATIONENTITYQUERY = "DoctorRelationEntityList";
    public static final String ATTACHMENTRELATIONENTITYQUERY = "AttachmentRelationEntityList";
    public static final String COMPETITIONRELATIONENTITYQUERY = "CompetitionRelationEntityList";
    public static final String COMPETITIONSCOUTRELATIONENTITYQUERY = "CompetitionScoutRelationEntityList";
    public static final String RESULTENTITYLISTQUERY = "ResultEntityList";
    public static final String RESULTTRIAENTITYLISTQUERY = "ResultTriaEntityList";
    public static final String PERSONPERSONQUERY = "PersonPersonRelationList";
    public static final String PERSONDOCTORQUERY = "PersonDoctorRelationList";
    public static final String PERSONATTACHMENTQUERY = "PersonAttachmentRelationList";
    public static final String RELTYPSVALUELISTQUERY = "RelTypsValueList";
    public static final String COUNTRYVALUELISTQUERY = "CountryValueList";
    public static final String CATEGORYVALUELISTQUERY = "CategoryValueList";
    public static final String TESTTYPEVALUELISTQUERY = "TestTypeValueList";
    public static final String COMPTYPEVALUELISTQUERY = "CompTypeValueList";
    public static final String COMPSUBTYPEVALUELISTQUERY = "CompSubtypeValueList";
    public static final String SCHEDULETYPEVALUELISTQUERY = "ScheduleTypeValueList";
    public static final String ENTITIESBYLABELLISTQUERY = "EntitiesByLabelList";
    public static final String SCHEDULETYPEDEFAULTCOLORQUERY = "ScheduleTypeDefaultColor";

    private HibernateTransactionManager transactionManager;
    private Dictionary dictionaryService;
    private DAOLayer daoLayer;
    private LogicLayer logicLayer;
    private SessionFactory sessionFactory;

    /**
     * Retrieve functiontree for workplace
     *
     * @param role_key
     *            role of user
     * @param lang_key
     *            language
     * @return functiontree
     */
    @SuppressWarnings("unchecked")
    public List<SFunctionTree> getFunctionTree(Constants.Role role, String lang_key) {
        List<SFunctionTree> data = new ArrayList<SFunctionTree>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(FUNCTIONTREEQUERY);
        query.setString("p_role_key", role.getName());
        query.setString("p_lang_key", lang_key);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            SFunctionTree datum = new SFunctionTree();
            int i = 0;
            datum.node = (Integer) line[i++];
            datum.parent = (Integer) line[i++];
            datum.order = (Integer) line[i++];
            try {
                datum.key = Constants.FunctionNode.valueOf(((String) line[i++]).toUpperCase());
            } catch (Exception ex) {
                logger.warn(ex.getMessage());
            }
            datum.page = (String) line[i++];
            datum.entity = (String) line[i++];
            datum.edit = (Boolean) line[i++];
            datum.create = (Boolean) line[i++];
            datum.delete = (Boolean) line[i++];
            datum.description = (String) line[i++];
            datum.description_long = (String) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    @SuppressWarnings("unchecked")
	public List<String> getEntityIds(SearchRange sr, Class claz) {
        Session session = sessionFactory.openSession();
        Criteria criteria = sr.buildCriteria(session, claz);
        List<IEntityObject> result = criteria.list();
        session.close();
        if(result==null || result.size()==0) return Collections.EMPTY_LIST;
        List<String> ids = new ArrayList<String>(result.size());
        for(IEntityObject item : result) {
            ids.add(item.getId());
        }
        return ids;
    }

    public List<SFunctionTree> getFunctionTree(Constants.Role role) {
        return getFunctionTree(role, Helper.getLanguageServer());
    }

    /**
     * Retrieve user entities
     *
     * @param lang_key
     *            language
     * @param deleted
     *            show deleted
     * @return user entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getUserEntities(String lang_key, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(USERENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            UserEntity.Data datum = new UserEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.user_name = (String) line[i++];
            datum.email = (String) line[i++];
            datum.language = (String) line[i++];
            datum.currency = (String) line[i++];
            datum.person = (String) line[i++];
            datum.locked = (Boolean) line[i++];
            datum.initial = (Boolean) line[i++];
            datum.active = (Boolean) line[i++];
            datum.last_login = (Timestamp) line[i++];
            datum.last_login_ip = (String) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<Schedules> getSchedules(String personId, Timestamp start, Timestamp end, Boolean template) {
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Schedules.class).setCacheable(true).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (!Helper.isEmpty(personId))
            criteria.add(Restrictions.eq("personId", personId));
        if (start != null)
            criteria.add(Restrictions.ge("start", start));
        if (end != null)
            criteria.add(Restrictions.le("start", end));
        if (template != null)
            criteria.add(Restrictions.eq("template", template));
        criteria.add(Restrictions.eq("deleted", false));

        // run query
        List<Schedules> schedules = criteria.list();
        session.close();
        return schedules;
    }

    public List<IEntityData> getUserEntities() {
        return getUserEntities(Helper.getLanguageServer(), false, false);
    }

    /**
     * Retrieve person entities
     *
     * @param lang_key
     * @param deleted
     * @param test
     * @param queryName
     * @return person entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getPersonEntities(String lang_key, boolean deleted, boolean test, String queryName, String parameterName, String parameterValue) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(queryName);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        // handle parameter
        query.setBoolean("p_id_on", false);
        query.setString("p_id", null);
        if (ID.equals(parameterName)) {
            query.setBoolean("p_id_on", true);
            query.setString("p_id", parameterValue);
        }

        List<Object[]> result = query.list();
        for (Object[] line : result) {
            PersonEntity.Data datum = new PersonEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.salutation = (String) line[i++];
            datum.nameFirst = (String) line[i++];
            datum.nameLast = (String) line[i++];
            datum.email = (String) line[i++];
            datum.sex = (String) line[i++];
            datum.birthdate = (Timestamp) line[i++];
            datum.street = (String) line[i++];
            datum.housenumber = (String) line[i++];
            datum.postcode = (String) line[i++];
            datum.city = (String) line[i++];
            datum.state = (String) line[i++];
            datum.country = (String) line[i++];
            datum.homepage = (String) line[i++];
            datum.telephone = (String) line[i++];
            datum.mobile = (String) line[i++];
            datum.fax = (String) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getPersonEntities() {
        return getPersonEntities(Helper.getLanguageServer(), false, false, PERSONENTITYLISTQUERY, null, null);
    }

    public List<IEntityData> getAthleteEntities() {
        return getPersonEntities(Helper.getLanguageServer(), false, false, ATHLETESENTITYLISTQUERY, null, null);
    }

    public List<IEntityData> getPersonEntities(String parameterName, String parameterValue) {
        return getPersonEntities(Helper.getLanguageServer(), false, false, PERSONENTITYLISTQUERY, parameterName, parameterValue);
    }

    /**
     * Retrieve person entities by role
     *
     * @param lang_key
     *            language
     * @param role
     *            role
     * @param deleted
     *            deleted
     * @param test
     *            test
     * @return person entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getPersonByRoleEntities(String lang_key, Role role, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(PERSONBYROLEENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        query.setString("p_role_key", role.name());
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            PersonEntity.Data datum = new PersonEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.salutation = (String) line[i++];
            datum.nameFirst = (String) line[i++];
            datum.nameLast = (String) line[i++];
            datum.email = (String) line[i++];
            datum.sex = (String) line[i++];
            datum.birthdate = (Timestamp) line[i++];
            datum.street = (String) line[i++];
            datum.housenumber = (String) line[i++];
            datum.postcode = (String) line[i++];
            datum.city = (String) line[i++];
            datum.state = (String) line[i++];
            datum.country = (String) line[i++];
            datum.homepage = (String) line[i++];
            datum.telephone = (String) line[i++];
            datum.mobile = (String) line[i++];
            datum.fax = (String) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getPersonByRoleEntities(Role role) {
        return getPersonByRoleEntities(Helper.getLanguageServer(), role, false, false);
    }

    /**
     * Retrieve doctor entities
     *
     * @param lang_key
     * @param deleted
     * @param test
     * @return doctor entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getDoctorEntities(String lang_key, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(DOCTORENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            DoctorEntity.Data datum = new DoctorEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.name = (String) line[i++];
            datum.street = (String) line[i++];
            datum.housenumber = (String) line[i++];
            datum.postcode = (String) line[i++];
            datum.city = (String) line[i++];
            datum.state = (String) line[i++];
            datum.country = (String) line[i++];
            datum.email = (String) line[i++];
            datum.homepage = (String) line[i++];
            datum.telephone = (String) line[i++];
            datum.mobile = (String) line[i++];
            datum.fax = (String) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getDoctorEntities() {
        return getDoctorEntities(Helper.getLanguageServer(), false, false);
    }

    /**
     * Retrieve attachment entities
     *
     * @param lang_key
     * @param deleted
     * @param test
     * @return attachment entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getAttachmentEntities(String lang_key, String parameterName, String parameterValue, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(ATTACHMENTENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        // handle parameter
        query.setBoolean("p_id_on", false);
        query.setString("p_id", null);
        if (ID.equals(parameterName)) {
            query.setBoolean("p_id_on", true);
            query.setString("p_id", parameterValue);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            AttachmentEntity.Data datum = new AttachmentEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.category = (String) line[i++];
            datum.description = (String) line[i++];
            datum.owner = (String) line[i++];
            datum.mimetype = (String) line[i++];
            datum.filename = (String) line[i++];
            datum.filesize = (Integer) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getAttachmentEntities() {
        return getAttachmentEntities(Helper.getLanguageServer(), null, null, false, false);
    }

    public List<IEntityData> getAttachmentEntities(String parameterName, String parameterValue) {
        return getAttachmentEntities(Helper.getLanguageServer(), parameterName, parameterValue, false, false);
    }

    /**
     * Retrieve test entities
     *
     * @param lang_key
     * @param deleted
     * @param test
     * @return test entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getTestEntities(String lang_key, String parameterName, String parameterValue, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(TESTENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        // handle parameter
        query.setBoolean("p_person_on", false);
        query.setBoolean("p_coach_on", false);
        query.setBoolean("p_id_on", false);
        query.setString("p_person", null);
        query.setString("p_coach", null);
        query.setString("p_id", null);
        if (TestEntity.PERSON.equals(parameterName)) {
            query.setBoolean("p_person_on", true);
            query.setString("p_person", parameterValue);
        }
        if (TestEntity.COACH.equals(parameterName)) {
            query.setBoolean("p_coach_on", true);
            query.setString("p_coach", parameterValue);
        }
        if (ID.equals(parameterName)) {
            query.setBoolean("p_id_on", true);
            query.setString("p_id", parameterValue);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            TestEntity.Data datum = new TestEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.person = (String) line[i++];
            datum.doctor = (String) line[i++];
            datum.coach = (String) line[i++];
            datum.type = (String) line[i++];
            datum.date = (Timestamp) line[i++];
            datum.description = (String) line[i++];
            datum.protocol = (Boolean) line[i++];
            datum.analyzed = (Boolean) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getTestEntities() {
        return getTestEntities(Helper.getLanguageServer(), null, null, false, false);
    }

    public List<IEntityData> getTestEntities(String parameterName, String parameterValue) {
        return getTestEntities(Helper.getLanguageServer(), parameterName, parameterValue, false, false);
    }

    /**
     * Retrieve competition entities
     *
     * @param lang_key
     * @param deleted
     * @param test
     * @return competition entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getCompetitionEntities(String lang_key, String parameterName, String parameterValue, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(COMPETITIONENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        // handle parameter
        query.setBoolean("p_id_on", false);
        query.setString("p_id", null);
        if (ID.equals(parameterName)) {
            query.setBoolean("p_id_on", true);
            query.setString("p_id", parameterValue);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            CompetitionEntity.Data datum = new CompetitionEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.date = (Timestamp) line[i++];
            datum.description = (String) line[i++];
            datum.type = (String) line[i++];
            datum.subtype = (String) line[i++];
            datum.address = (String) line[i++];
            datum.country = (String) line[i++];
            datum.results = (Boolean) line[i++];
            datum.resultslist = (Boolean) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    /**
     * Retrieve competitions in a certain relationship
     *
     * @param person_id
     * @param relation
     * @param lang_key
     * @param deleted
     * @param test
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getCompetitionRelationEntities(String person_id, Constants.Relation relation, String lang_key, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = null;
        if (relation == Constants.Relation.COMPETITIONSCOUT) {
            // special handling for scouts relation
            query = session.getNamedQuery(COMPETITIONSCOUTRELATIONENTITYQUERY);
            query.setString("p_lang_key", lang_key);
            query.setBoolean("p_deleted", deleted);
            query.setBoolean("p_test", test);
            query.setString("p_scout", person_id);
        } else {
            query = session.getNamedQuery(COMPETITIONRELATIONENTITYQUERY);
            query.setString("p_lang_key", lang_key);
            query.setBoolean("p_deleted", deleted);
            query.setBoolean("p_test", test);
            query.setString("p_reltyp", relation.type());
            query.setString("p_person", person_id);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            CompetitionEntity.Data datum = new CompetitionEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.date = (Timestamp) line[i++];
            datum.description = (String) line[i++];
            datum.type = (String) line[i++];
            datum.subtype = (String) line[i++];
            datum.address = (String) line[i++];
            datum.country = (String) line[i++];
            datum.results = (Boolean) line[i++];
            datum.resultslist = (Boolean) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getCompetitionRelationEntities(String person_id, Constants.Relation relation) {
        return getCompetitionRelationEntities(person_id, relation, Helper.getLanguageServer(), false, false);
    }

    public List<IEntityData> getCompetitionEntities() {
        return getCompetitionEntities(Helper.getLanguageServer(), null, null, false, false);
    }

    public List<IEntityData> getCompetitionEntities(String parameterName, String parameterValue) {
        return getCompetitionEntities(Helper.getLanguageServer(), parameterName, parameterValue, false, false);
    }

    /**
     * Retrieve result entities
     *
     * @param lang_key
     *            language
     * @param id
     *            competition
     * @param scoutId
     *            scout
     * @param athleteId
     *            athlete
     * @param deleted
     *            deleted
     * @param test
     *            test
     * @return result entities
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getResultEntities(String lang_key, String id, String competitionId, String scoutId, String athleteId, String compType, String compSubtype, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(RESULTENTITYLISTQUERY);
        if (CompetitionEntity.TRIATHLON.equals(compType) || CompetitionEntity.XTERRA.equals(compType)) {
            query = session.getNamedQuery(RESULTTRIAENTITYLISTQUERY);
            query.setString("p_type", compType);
            query.setString("p_subtype", compSubtype);
            if (compSubtype == null) {
                query.setBoolean("p_subtype_on", false);
            } else {
                query.setBoolean("p_subtype_on", true);
            }
        }
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        // handle parameter
        query.setString("p_id", id);
        if (id == null) {
            query.setBoolean("p_id_on", false);
        } else {
            query.setBoolean("p_id_on", true);
        }
        query.setString("p_competition", competitionId);
        if (competitionId == null) {
            query.setBoolean("p_competition_on", false);
        } else {
            query.setBoolean("p_competition_on", true);
        }
        query.setString("p_scout", scoutId);
        if (scoutId == null) {
            query.setBoolean("p_scout_on", false);
        } else {
            query.setBoolean("p_scout_on", true);
        }
        query.setString("p_athlete", athleteId);
        if (athleteId == null) {
            query.setBoolean("p_athlete_on", false);
        } else {
            query.setBoolean("p_athlete_on", true);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            ResultEntity.Data datum = new ResultEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.date = (Timestamp) line[i++];
            datum.competition = (String) line[i++];
            datum.type = (String) line[i++];
            datum.subtype = (String) line[i++];
            datum.scout = (String) line[i++];
            datum.athlete = (String) line[i++];
            datum.final_position = (String) line[i++];
            datum.time = (String) line[i++];
            datum.comment = (String) line[i++];
            // triathlon
            if (CompetitionEntity.TRIATHLON.equals(compType) || CompetitionEntity.XTERRA.equals(compType)) {
                datum.category_tria = (String) line[i++];
                datum.swim_split = (String) line[i++];
                datum.swim_pos = (String) line[i++];
                datum.run_split = (String) line[i++];
                datum.run_pos = (String) line[i++];
                datum.bike_split = (String) line[i++];
                datum.bike_pos = (String) line[i++];
                // get limits
                Map<String, Limit> limitsMap = logicLayer.getCompetitionLogic().getLimitsMap((String) line[i++]);
                Limit limit = limitsMap.get(datum.category_tria);
                if (limit != null) {
                    datum.swim_cutoff = limit.getLimits()[0];
                    datum.run_cutoff = limit.getLimits()[1];
                    datum.swim_tol = limit.getTolerances()[0];
                    datum.run_tol = limit.getTolerances()[1];
                    datum.best_runner = limit.getRun()[0];
                    datum.best_run_split = limit.getRun()[1];
                    datum.best_swimmer = limit.getSwim()[0];
                    datum.best_swim_split = limit.getSwim()[1];
                    datum.swimsuit = limit.getSwimsuit();
                    // TODO: this was added after first release
                    if (limit.getLimits().length > 2) {
                        datum.bike_cutoff = limit.getLimits()[2];
                        datum.best_biker = limit.getBike()[0];
                        datum.best_bike_split = limit.getBike()[1];
                        datum.bike_tol = limit.getTolerances()[2];
                    }
                }
            }
            data.add(datum);
        }
        session.close();
        return data;
    }

    /**
     * Get all results
     *
     * @return
     */
    public List<IEntityData> getResultEntities() {
        return getResultEntities(Helper.getLanguageServer(), null, null, null, null, null, null, false, false);
    }

    /**
     * @param id
     *            ID
     * @param competitionId
     *            Competition ID
     * @param scoutId
     *            Scout ID
     * @param athleteId
     *            Athlete ID
     * @param compType
     *            Competition Type
     * @return Result entities
     */
    public List<IEntityData> getResultEntities(String id, String competitionId, String scoutId, String athleteId, String compType, String compSubtype) {
        return getResultEntities(Helper.getLanguageServer(), id, competitionId, scoutId, athleteId, compType, compSubtype, false, false);
    }

    /**
     * @param lang_key
     * @param id
     * @param personId
     * @param startLow
     * @param startHigh
     * @param deleted
     * @param test
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getScheduleEntities(String lang_key, String id, String personId, Timestamp startLow, Timestamp startHigh, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(SCHEDULEENTITYLISTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        // handle parameter
        query.setString("p_id", id);
        if (id == null) {
            query.setBoolean("p_id_on", false);
        } else {
            query.setBoolean("p_id_on", true);
        }
        query.setString("p_person", personId);
        if (personId == null) {
            query.setBoolean("p_person_on", false);
        } else {
            query.setBoolean("p_person_on", true);
        }
        query.setTimestamp("p_start_low", startLow);
        query.setTimestamp("p_start_high", startHigh);
        if (startLow == null || startHigh == null) {
            query.setBoolean("p_start_on", false);
        } else {
            query.setBoolean("p_start_on", true);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            ScheduleEntity.Data datum = new ScheduleEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.person = (String) line[i++];
            datum.type = (String) line[i++];
            datum.description = (String) line[i++];
            datum.start = (Timestamp) line[i++];
            datum.duration = (Long) line[i++];
            datum.color = (String) line[i++];
            datum.template = (Boolean) line[i++];
            datum.done = (Boolean) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    /**
     * Get all schedules
     *
     * @return
     */
    public List<IEntityData> getScheduleEntities() {
        return getScheduleEntities(Helper.getLanguageServer(), null, null, null, null, false, false);
    }

    /**
     * @param id
     * @param personId
     * @param startLow
     * @param startHigh
     * @return
     */
    public List<IEntityData> getScheduleEntities(String id, String personId, Timestamp startLow, Timestamp startHigh) {
        return getScheduleEntities(Helper.getLanguageServer(), id, personId, startLow, startHigh, false, false);
    }

    public List<IEntityData> getScheduleEntities(String id) {
        return getScheduleEntities(Helper.getLanguageServer(), id, null, null, null, false, false);
    }

    /**
     * Retrieve persons in a certain relationship
     *
     * @param person_id
     * @param relation
     * @param inverse
     * @param lang_key
     * @param deleted
     * @param test
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getPersonRelationEntities(String person_id, Constants.Relation relation, boolean inverse, String lang_key, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(PERSONRELATIONENTITYQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        query.setString("p_reltyp", relation.type());
        if (inverse) {
            query.setString("p_partner1", person_id);
            query.setString("p_partner2", null);
        } else {
            query.setString("p_partner1", null);
            query.setString("p_partner2", person_id);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            PersonEntity.Data datum = new PersonEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.salutation = (String) line[i++];
            datum.nameFirst = (String) line[i++];
            datum.nameLast = (String) line[i++];
            datum.email = (String) line[i++];
            datum.sex = (String) line[i++];
            datum.birthdate = (Timestamp) line[i++];
            datum.street = (String) line[i++];
            datum.housenumber = (String) line[i++];
            datum.postcode = (String) line[i++];
            datum.city = (String) line[i++];
            datum.state = (String) line[i++];
            datum.country = (String) line[i++];
            datum.homepage = (String) line[i++];
            datum.telephone = (String) line[i++];
            datum.mobile = (String) line[i++];
            datum.fax = (String) line[i++];
            datum.standard = (Boolean) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getPersonRelationEntities(String person_id, Constants.Relation relation, boolean inverse) {
        return getPersonRelationEntities(person_id, relation, inverse, Helper.getLanguageServer(), false, false);
    }

    /**
     * Return person to person relations
     *
     * @param relation
     * @param lang_key
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IRelationData> getPersonPersonRelation(Constants.Relation relation, String lang_key) {
        List<IRelationData> data = new ArrayList<IRelationData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(PERSONPERSONQUERY);
        query.setString("p_lang_key", lang_key);
        query.setString("p_reltyp", relation.type());
        // when base Relation then return all
        if (relation == Constants.Relation.PERSONPERSON) {
            query.setInteger("p_dummy", 1);
        } else {
            query.setInteger("p_dummy", 0);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            PersonPersonRelation.Data datum = new PersonPersonRelation.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.partner1 = daoLayer.getPersonsDAO().findById((String) line[i++]);
            datum.description = (String) line[i++];
            datum.description_inverse = (String) line[i++];
            datum.standard = (Boolean) line[i++];
            datum.reltyp = (String) line[i++];
            datum.partner2 = daoLayer.getPersonsDAO().findById((String) line[i++]);
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IRelationData> getPersonPersonRelation(Constants.Relation relation) {
        return getPersonPersonRelation(relation, Helper.getLanguageServer());
    }

    /**
     * Retrieve doctors in a certain relationship
     *
     * @param partner_id
     * @param relation
     * @param inverse
     * @param lang_key
     * @param deleted
     * @param test
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getDoctorRelationEntities(String person_id, Constants.Relation relation, String lang_key, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(DOCTORRELATIONENTITYQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        query.setString("p_reltyp", relation.type());
        query.setString("p_person", person_id);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            DoctorEntity.Data datum = new DoctorEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.name = (String) line[i++];
            datum.street = (String) line[i++];
            datum.housenumber = (String) line[i++];
            datum.postcode = (String) line[i++];
            datum.city = (String) line[i++];
            datum.state = (String) line[i++];
            datum.country = (String) line[i++];
            datum.email = (String) line[i++];
            datum.homepage = (String) line[i++];
            datum.telephone = (String) line[i++];
            datum.mobile = (String) line[i++];
            datum.fax = (String) line[i++];
            datum.standard = (Boolean) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getDoctorRelationEntities(String person_id, Constants.Relation relation) {
        return getDoctorRelationEntities(person_id, relation, Helper.getLanguageServer(), false, false);
    }

    /**
     * Retrieve attachments in a certain relationship
     *
     * @param person_id
     * @param relation
     * @param lang_key
     * @param deleted
     * @param test
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IEntityData> getAttachmentRelationEntities(String person_id, Constants.Relation relation, String lang_key, boolean deleted, boolean test) {
        List<IEntityData> data = new ArrayList<IEntityData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(ATTACHMENTRELATIONENTITYQUERY);
        query.setString("p_lang_key", lang_key);
        query.setBoolean("p_deleted", deleted);
        query.setBoolean("p_test", test);
        query.setString("p_reltyp", relation.type());
        query.setString("p_person", person_id);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            AttachmentEntity.Data datum = new AttachmentEntity.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.category = (String) line[i++];
            datum.description = (String) line[i++];
            datum.owner = (String) line[i++];
            datum.mimetype = (String) line[i++];
            datum.filename = (String) line[i++];
            datum.filesize = (Integer) line[i++];
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IEntityData> getAttachmentRelationEntities(String person_id, Constants.Relation relation) {
        return getAttachmentRelationEntities(person_id, relation, Helper.getLanguageServer(), false, false);
    }

    /**
     * Return person to doctor relations
     *
     * @param relation
     * @param lang_key
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IRelationData> getPersonDoctorRelation(Constants.Relation relation, String lang_key) {
        List<IRelationData> data = new ArrayList<IRelationData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(PERSONDOCTORQUERY);
        query.setString("p_lang_key", lang_key);
        query.setString("p_reltyp", relation.type());
        // when base Relation then return all
        if (relation == Constants.Relation.PERSONDOCTOR) {
            query.setInteger("p_dummy", 1);
        } else {
            query.setInteger("p_dummy", 0);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            PersonDoctorRelation.Data datum = new PersonDoctorRelation.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.person = daoLayer.getPersonsDAO().findById((String) line[i++]);
            datum.description = (String) line[i++];
            datum.description_inverse = (String) line[i++];
            datum.standard = (Boolean) line[i++];
            datum.reltyp = (String) line[i++];
            datum.doctor = daoLayer.getDoctorsDAO().findById((String) line[i++]);
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IRelationData> getPersonDoctorRelation(Constants.Relation relation) {
        return getPersonDoctorRelation(relation, Helper.getLanguageServer());
    }

    /**
     * Return person to doctor relations
     *
     * @param relation
     * @param lang_key
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IRelationData> getPersonAttachmentRelation(Constants.Relation relation, String lang_key) {
        List<IRelationData> data = new ArrayList<IRelationData>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(PERSONATTACHMENTQUERY);
        query.setString("p_lang_key", lang_key);
        query.setString("p_reltyp", relation.type());
        // when base Relation then return all
        if (relation == Constants.Relation.PERSONATTACHMENT) {
            query.setInteger("p_dummy", 1);
        } else {
            query.setInteger("p_dummy", 0);
        }
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            PersonAttachmentRelation.Data datum = new PersonAttachmentRelation.Data();
            int i = 0;
            datum.id = (String) line[i++];
            datum.person = daoLayer.getPersonsDAO().findById((String) line[i++]);
            datum.description = (String) line[i++];
            datum.description_inverse = (String) line[i++];
            datum.standard = (Boolean) line[i++];
            datum.reltyp = (String) line[i++];
            datum.attachment = daoLayer.getAttachmentsDAO().findById((String) line[i++]);
            data.add(datum);
        }
        session.close();
        return data;
    }

    public List<IRelationData> getPersonAttachmentRelation(Constants.Relation relation) {
        return getPersonAttachmentRelation(relation, Helper.getLanguageServer());
    }

    /**
     * Retrieve value list
     *
     * @param valueList
     * @param lang_key
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SValueList> getValueList(Constants.ValueList valueList, String lang_key, String parent_key) {
        List<SValueList> list = new ArrayList<SValueList>();
        String namedQuery = valueList.getNamedQuery();
        if (Helper.isEmpty(namedQuery)) {
            logger.warn("Valuelist not found: " + valueList.name());
            return list;
        }
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(namedQuery);
        query.setString("p_lang_key", lang_key);
        if (!Helper.isEmpty(parent_key))
            query.setString("p_parent_key", parent_key);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            SValueList entry = new SValueList();
            int i = 0;
            entry.key = (String) line[i++];
            entry.description = (String) line[i++];
            entry.descriptionLong = (String) line[i++];
            list.add(entry);
        }
        session.close();
        return list;
    }

    @SuppressWarnings("unchecked")
    public String getScheduleTypeDefaultColor(String type) {
    	Session session = sessionFactory.openSession();
    	Query query = session.getNamedQuery(SCHEDULETYPEDEFAULTCOLORQUERY);
    	query.setString("p_key", type);
    	List<String> result = query.list();
    	if(result.isEmpty()) return Constants.WHITE;
    	session.close();
       return result.get(0);
    }

    /**
     * Retrieve all entities and ids for a certain label
     *
     * @param labelId
     *            Label
     * @param deleted
     *            deleted
     * @return entities which have the label assigned
     */
    public Map<Constants.Entity, List<String>> getEntitiesByLabelList(String labelId, boolean deleted) {
        Map<Constants.Entity, List<String>> map = new HashMap<Constants.Entity, List<String>>();
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery(ENTITIESBYLABELLISTQUERY);
        query.setString("p_label_id", labelId);
        query.setBoolean("p_deleted", deleted);
        List<Object[]> result = query.list();
        for (Object[] line : result) {
            try {
                Constants.Entity entity = Constants.Entity.valueOf(((String) line[0]).toUpperCase());
                String entityId = (String) line[1];
                // check if entity already in map
                if (!map.containsKey(entity))
                    map.put(entity, new ArrayList<String>());
                // add id
                map.get(entity).add(entityId);
            } catch (Exception ex) {
                Statusbar.outputAlert(Helper.getMessages("entity_wrong"), Helper.getLiteral("error"), Helper.getMessages("entity_wrong_detail")).setLeftTopReferenceCentered();
                continue;
            }

        }
        session.close();
        return map;
    }

    public Map<Constants.Entity, List<String>> getEntitiesByLabelList(String labelId) {
        return getEntitiesByLabelList(labelId, false);
    }

    public int deleteAllSwimProtocols(String id) {
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete TestsSwimProtocol where id.id = :p_id");
        query.setString("p_id", id);
        return query.executeUpdate();
    }

    public int deleteAllSchedulesDetail(String id) {
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete SchedulesDetail where id.id = :p_id");
        query.setString("p_id", id);
        return query.executeUpdate();
    }

    public Results checkResultExists(String competitionId, String scoutId, String athleteId) {
        if (Helper.isEmpty(competitionId) || Helper.isEmpty(scoutId) || Helper.isEmpty(athleteId))
            return null;
        // SessionFactory sessionFactory =
        // transactionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        // TODO make a bit nicer
        String query = "from trimatrix.db.Results as model where model.competitionId = '" + competitionId + "' and model.scoutId = '" + scoutId + "' and model.athleteId = '" + athleteId
                + "' and deleted = 0";
        Results result = null;
        try {
            result = (Results) session.createQuery(query).iterate().next();
        } catch (Exception ex) {
        }
        session.close();
        return result;
    }

    public List<Integer> getDistinctDistances(String athleteId) {
        List<Integer> result = new ArrayList<Integer>();
        Session session = sessionFactory.openSession();
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.property("id.distance"));
        Criteria criteria = session.createCriteria(ZonesSwim.class);
        criteria.add(Restrictions.eq("id.athleteId", athleteId));
        criteria.addOrder(Property.forName("id.distance").asc());
        criteria.setProjection(Projections.distinct(proList));
        try {
            result = criteria.list();
        } catch (RuntimeException re) {
        }
        session.close();
        return result;
    }

    public void setTransactionManager(HibernateTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setDictionaryService(Dictionary dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public void setDaoLayer(DAOLayer daoLayer) {
        this.daoLayer = daoLayer;
    }

    public void setLogicLayer(LogicLayer logicLayer) {
        this.logicLayer = logicLayer;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static SQLExecutorService getFromApplicationContext(ApplicationContext ctx) {
        return (SQLExecutorService) ctx.getBean("sqlExecutorService");
    }
}
