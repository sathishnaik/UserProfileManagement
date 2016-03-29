package com.cg.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.springmvc.model.Otp;

@Transactional
@Repository
public class OtpDAOImpl implements OtpDAO {

	@PersistenceContext
    private EntityManager manager;

	@Override
	public void addOtp(Otp otp) {
		manager.persist(otp);
	}

	@Override
	public Otp updateOtp(Otp otp) {
		return (Otp) manager.merge(otp);
	}

	@Override
	public void removeOtp(Integer id) {
		Otp otp = (Otp) manager.find(Otp.class, id);
		if (null != otp) {
			manager.remove(otp);
		}
	}

	@Override
	public Otp getOtpById(Integer otpId) {
		List<Otp> list = manager.createQuery("select o from Otp u where o.id = :otpId", Otp.class).setParameter("otpId", otpId).getResultList();
		return list.size() > 0 ? (Otp) list.get(0) : null;
	}

}
