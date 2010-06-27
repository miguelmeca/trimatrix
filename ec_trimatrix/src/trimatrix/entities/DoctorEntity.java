package trimatrix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Doctors;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

public class DoctorEntity extends AEntity {
	public DoctorEntity() {
		super(Doctors.class);
	}

	// Constants
	public static final String NAME = "name";
	public static final String STREET = "street";
    public static final String HOUSENUMBER = "housenumber";
    public static final String POSTCODE = "postcode";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";
    public static final String EMAIL = "email";
    public static final String HOMEPAGE = "homepage";
    public static final String TELEPHONE = "telephone";
    public static final String MOBILE = "mobile";
    public static final String FAX = "fax";

	public IEntityObject create() {
		String id = UUID.randomUUID().toString();
		Doctors entity = new Doctors();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);
		return entity;
	}

	@Override
	public boolean delete(final String id) {
		// all in one transaction
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Boolean result = (Boolean)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					Doctors entity = (Doctors)entitiesDAO.findById(id);
					if(entity==null) return false;
					// check if user admin or creator
					if (dictionaryService.getMyRoles().contains(Constants.Role.ADMIN.getName())||entity.getCreatedBy().equals(dictionaryService.getMyUser().getId())) {
						entity.setDeleted(true);
						entitiesDAO.merge(entity);
						int deleted = daoLayer.deleteRelationsByPartner(id);
						Statusbar.outputSuccess(String.format(Helper.getMessages("del_entity_success"), deleted));
					} else {
						Statusbar.outputAlert(Helper.getMessages("del_entity_admin")).setLeftTopReferenceCentered();
						return false;
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}
				logger.info("DoctorEntity : Deletion of doctor successful => " + id );
				return true;
			}
		});
		return result;
	}

	public List<IEntityData> getData(Entity entity, String personId, String filter) {
		if (entity == Constants.Entity.DOCTOR) {
        	return sqlExecutorService.getDoctorRelationEntities(personId, Constants.Relation.DOCTOR);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	public List<IEntityData> getData(Entity entity, String filter) {
		if (entity == Constants.Entity.DOCTOR) {
        	return sqlExecutorService.getDoctorEntities();
        } else if (entity == Constants.Entity.MYDOCTORS) {
        	return sqlExecutorService.getDoctorRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.DOCTOR);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	public List<IEntityData> getData() {
		return sqlExecutorService.getDoctorEntities();
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("name"), NAME, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("street"), STREET, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("housenumber"), HOUSENUMBER, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("postcode"), POSTCODE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("city"), CITY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("state"), STATE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("country"), COUNTRY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("email"), EMAIL, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("homepage"), HOMEPAGE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("telephone"), TELEPHONE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("mobile"), MOBILE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("fax"), FAX, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public static class Data implements IEntityData {
		public String id;
		public String name;
		public String street;
		public String housenumber;
		public String postcode;
		public String city;
		public String state;
		public String country;
		public String email;
		public String homepage;
		public String telephone;
		public String mobile;
		public String fax;
		public Boolean standard;

		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			// same as DB entity implementation
			return name.replace(Constants.NULL, Constants.EMPTY).trim();
		}

		public String getName() {
			return name;
		}

		public String getStreet() {
			return street;
		}

		public String getHousenumber() {
			return housenumber;
		}

		public String getPostcode() {
			return postcode;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getCountry() {
			return country;
		}

		public String getEmail() {
			return email;
		}

		public String getHomepage() {
			return homepage;
		}

		public String getTelephone() {
			return telephone;
		}

		public String getMobile() {
			return mobile;
		}

		public String getFax() {
			return fax;
		}

		public Boolean getStandard() {
			return standard;
		}
	}
}
