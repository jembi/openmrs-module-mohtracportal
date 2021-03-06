/**
 * 
 */
package org.openmrs.module.mohtracportal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.DrugOrder;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.OrderFrequency;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.mohtracportal.SampleCode;
import org.openmrs.module.mohtracportal.Sponsor;
import org.openmrs.module.mohtracportal.SponsorLocation;
import org.openmrs.module.mohtracportal.db.MohTracPortalDAO;
import org.openmrs.module.mohtracportal.service.MohTracPortalService;

/**
 * @author Yves GAKUBA
 * 
 */
public class MohTracPortalServiceImpl implements MohTracPortalService {

	private MohTracPortalDAO portalDao;

	/**
	 * @return the portalDao
	 */
	public MohTracPortalDAO getPortalDao() {
		return portalDao;
	}

	/**
	 * @param portalDao
	 *            the portalDao to set
	 */
	public void setPortalDao(MohTracPortalDAO portalDao) {
		this.portalDao = portalDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openmrs.module.mohtracportal.service.MohTracPortalService#getAllSponsors
	 * ()
	 */
	@Override
	public List<Sponsor> getAllSponsors() throws Exception {
		return portalDao.getAllSponsors();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openmrs.module.mohtracportal.service.MohTracPortalService#getSponsorById
	 * (java.lang.Integer)
	 */
	@Override
	public Sponsor getSponsorById(Integer sponsorId) throws Exception {
		return portalDao.getSponsorById(sponsorId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openmrs.module.mohtracportal.service.MohTracPortalService#saveSponsor
	 * (org.openmrs.module.mohtracportal.Sponsor)
	 */
	@Override
	public void saveSponsor(Sponsor sp) throws Exception {
		portalDao.saveSponsor(sp);
	}

	@Override
	public List<SampleCode> getAllSampleTestByLocation(Location location)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SampleCode> getAllSampleTestByPerson(Person personId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SampleCode> getAllSampleTestByTestType(Concept testConceptId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SampleCode> getAllSampleTests() throws Exception {
		return portalDao.getAllSampleTests();
	}

	@Override
	public SampleCode getSampleTestById(Integer sampleCodeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSampleTest(SampleCode sc) throws SQLException {
		portalDao.saveSampleTest(sc);
	}

	@Override
	public SampleCode getSampleTestBySampleCode(String sampleCode)
			throws Exception {
		return portalDao.getSampleTestBySampleCode(sampleCode);
	}

	@Override
	public SampleCode getSampleTestByObs(Obs o) throws Exception {
		return portalDao.getSampleTestByObs(o);
	}

	@Override
	public List<String> getAllSampleCodes() throws Exception {
		return portalDao.getAllSampleCodes();
	}

	@Override
	public List<String> getAllSampleCodesByResultStatus(String status)
			throws Exception {
		return portalDao.getAllSampleCodesByResultStatus(status);
	}

	@Override
	public List<SponsorLocation> getAllLocationSponsors() throws Exception {
		return portalDao.getAllLocationSponsors();
	}

	@Override
	public SponsorLocation getLocationSponsorById(Integer locationSponsorId)
			throws Exception {
		return portalDao.getLocationSponsorById(locationSponsorId);
	}

	@Override
	public void saveLocationSponsor(SponsorLocation spLoc) throws Exception {
		portalDao.saveLocationSponsor(spLoc);
	}

	@Override
	public List<Integer> getAllLocationSponsorIds() throws Exception {
		return portalDao.getAllLocationSponsorIds();
	}

	@Override
	public List<Integer> getLocationBySponsorId(Integer sponsorId)
			throws Exception {
		return portalDao.getLocationBySponsorId(sponsorId);
	}

	@Override
	public Sponsor getSponsorByLocationId(Integer locationId) throws Exception {
		return portalDao.getSponsorByLocationId(locationId);
	}

	@Override
	public List<Integer> getSponsorListByLocationId(Integer sponsorId)
			throws Exception {
		return portalDao.getSponsorListByLocationId(sponsorId);
	}

	public List<Object> getPatientIdByRegistrationDate(String dateFrom,
			String dateTo, List<Integer> selectedUsers) throws Exception {
		return portalDao.getPatientIdByRegistrationDate(dateFrom, dateTo,
				selectedUsers);
	}

	public String getDateOfFirstRecordByObjectAndByUser(Integer userId,
			Integer objectId) throws Exception {
		return portalDao.getDateOfFirstOrLastRecordByObjectAndByUser(userId,
				objectId, 0);
	}

	public String getDateOfLastRecordByObjectAndByUser(Integer userId,
			Integer objectId) throws Exception {
		return portalDao.getDateOfFirstOrLastRecordByObjectAndByUser(userId,
				objectId, 1);
	}

	public String getNumberOfRecordCreatedByObjectAndByUser(Integer userId,
			Integer objectId, Integer period) throws Exception {
		return portalDao.getNumberOfRecordCreatedByObjectAndByUser(userId,
				objectId, period);
	}

	@Override
	public int executeMySQLCommand(String sql) {
		return portalDao.executeMySQLCommand(sql);
	}
	
	@Override
	public List<DrugOrder> getDrugOrdersByPatient(Patient patient) {
		List<Order> orderList = Context.getOrderService().getOrders(patient, Context.getOrderService().getCareSettingByUuid("6f0c9a92-6f24-11e3-af88-005056821db0"), Context.getOrderService().getOrderTypeByName("Drug order"), false);//TODO, careseting should't be hard-coded to OUTPATIENT as here
		List<DrugOrder> drugOrders = new ArrayList<DrugOrder>();
		
		for(Order order: orderList) {
			drugOrders.add((DrugOrder) order);
		}
		return drugOrders;
	}
	
	@Override
	public List<OrderType> getAllOrderTypes(boolean includeRetired) {
		return portalDao.getAllOrderTypes(includeRetired);
	}
	
	/**
	 * see {@link MohTracPortalService#persistAndOrFetchOrderFrequency(String)}
	 */
	@Override
	public OrderFrequency persistAndOrFetchOrderFrequency(String codedConceptName, Double freqPerDay) {
		Concept existingConcept = Context.getConceptService().getConceptByName(codedConceptName);
		Concept concept = null;
		
		if(existingConcept != null && existingConcept.getConceptClass().getName().equals("Frequency")) {
			concept = existingConcept;
			
			return Context.getOrderService().getOrderFrequencyByConcept(concept);
		} else {
			concept = new Concept();
			concept.addName(new ConceptName(codedConceptName, Context.getLocale()));
			concept.setConceptClass(Context.getConceptService().getConceptClassByName("Frequency"));
			concept = Context.getConceptService().saveConcept(concept);
			OrderFrequency orderFrequency = new OrderFrequency();
			orderFrequency.setConcept(concept);
			orderFrequency.setFrequencyPerDay(freqPerDay == null ? 2d : freqPerDay);
			
			return Context.getOrderService().saveOrderFrequency(orderFrequency);
		}
	}
}
