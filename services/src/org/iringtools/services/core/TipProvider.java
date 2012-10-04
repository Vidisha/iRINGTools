package org.iringtools.services.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.iringtools.mapping.Classes;
import org.iringtools.mapping.ClassesList;
import org.iringtools.mapping.ComTipPk;
import org.iringtools.mapping.Commodity;
import org.iringtools.mapping.CommodityList;
import org.iringtools.mapping.CommodityTip;
import org.iringtools.mapping.CommodityTipList;
import org.iringtools.mapping.Ordinal;
import org.iringtools.mapping.OrdinalList;
import org.iringtools.mapping.OrdinalPk;
import org.iringtools.mapping.Patterns;
import org.iringtools.mapping.PatternsList;
import org.iringtools.mapping.Role;
import org.iringtools.mapping.RoleList;
import org.iringtools.mapping.RolePk;
import org.iringtools.mapping.Template;
import org.iringtools.mapping.TemplateMapList;
import org.iringtools.mapping.Tips;
import org.iringtools.mapping.TipsList;

public class TipProvider {
	private Map<String, Object> _settings;
	TemplateMapList tml = new TemplateMapList();
	PatternsList pl = new PatternsList();
	CommodityList coml = new CommodityList();
	CommodityTipList comTipl = new CommodityTipList();
	RoleList rml = new RoleList();
	ClassesList cl = new ClassesList();
	Classes c = new Classes();
	Role role = new Role();
	Ordinal ord = new Ordinal();
	OrdinalList ol = new OrdinalList();
	RoleList rl = new RoleList();
	TipsList tl = new TipsList();
	private static final Logger logger = Logger.getLogger(TipProvider.class);

	EntityManagerFactory emf;
	EntityTransaction tx;
	EntityManager em;

	public TipProvider(Map<String, Object> settings) {

		_settings = settings;

	}

	// .............Get, Put, Delete and Post of Commodity..................
	public CommodityList getCommodity() throws Exception {
		List<Commodity> comresults = new ArrayList<Commodity>();

		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from Commodity").getResultList();
			comresults = resultList;
			for (Commodity event : comresults) {
				event.setCommo(null);
			}
			coml.setItems(comresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return coml;
	}

	/*
	 * public CommodityList postCommodity(String id, String name, String
	 * rdlClassId) throws Exception { List<Commodity> comresults = new
	 * ArrayList<Commodity>();
	 * 
	 * try { emf = Persistence.createEntityManagerFactory("Tips-Jpa"); em =
	 * emf.createEntityManager(); tx = em.getTransaction(); tx.begin();
	 * Commodity com = new Commodity(); com.setId(id); com.setName(name);
	 * com.setRDLClassId(rdlClassId); em.persist(com);tx.commit(); List
	 * resultList = em.createQuery("from Commodity").getResultList(); comresults
	 * = resultList; coml.setItems(comresults);
	 * 
	 * } catch (PersistenceException e) { e.printStackTrace(); } finally {
	 * emf.close(); } return coml; }
	 */

	public void postCommodity(CommodityList comlist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = comlist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void putCommodity(CommodityList clist) throws Exception {
		Commodity c = new Commodity();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = clist.getItems();
			for (int i = 0; i < result.size(); i++) {
				c = (Commodity) (result.get(i));
				Commodity cc = em.find(Commodity.class, c.getId());
				cc.setRdlClassId(c.getRdlClassId());
				cc.setName(c.getName());
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteCommodity(String id) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Commodity com = em.find(Commodity.class, id);
			em.remove(com);
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	// ................Get, Put, Delete and Post Of Tip............................

	public TipsList getTips() throws Exception {
		List<Tips> tipresults = new ArrayList<Tips>();
		List<Patterns> paresults = new ArrayList<Patterns>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from Tips").getResultList();
			tipresults = resultList;
			for (Tips event : tipresults) {
				System.out.println("Tip (" + event.getId() + ") : "
						+ event.getName());
				paresults = event.getPatId();
				event.setPatId(null);
				event.setCommo(null);
			}
			tl.setItems(tipresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return tl;
	}

	public void postTips(TipsList tiplist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = tiplist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void putTips(TipsList tiplist) throws Exception {
		Tips t = new Tips();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = tiplist.getItems();
			for (int i = 0; i < result.size(); i++) {
				t = (Tips) (result.get(i));
				Tips tt = em.find(Tips.class, t.getId());
				tt.setDescription(t.getDescription());
				tt.setName(t.getName());
				tt.setLevel(t.getLevel());
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteTip(String id) throws Exception {
		List<Tips> tipresults = new ArrayList<Tips>();
		List<Patterns> paresults = new ArrayList<Patterns>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Tips tt = em.find(Tips.class, id);
			em.remove(tt);
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public TipsList getTip(String id) throws Exception {
		List<Tips> tipresults = new ArrayList<Tips>();
		List<Patterns> paresults = new ArrayList<Patterns>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("from Tips t where t.id = :id");
			query.setParameter("id", id);
			List resultList = query.getResultList();
			tipresults = resultList;
			for (Tips event : tipresults) {
				event.setPatId(null);
				event.setCommo(null);
			}
			tl.setItems(tipresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return tl;
	}

	public TipsList getTipPatterns(String id) {
		List<Tips> tipresults = new ArrayList<Tips>();
		List<Ordinal> ordresults = new ArrayList<Ordinal>();
		List<Patterns> paresults = new ArrayList<Patterns>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("from Tips t where t.id = :id");
			query.setParameter("id", id);
			List resultList = query.getResultList();
			tipresults = resultList;
			for (Tips event : tipresults) {
				Query query1 = em
						.createQuery("from Patterns p where p.tipId = :id");
				query1.setParameter("id", id);
				List resultList1 = query1.getResultList();
				paresults = resultList1;
				System.out.println("pattern id is ::" + paresults);
				event.setPatId(paresults);
				event.setCommo(null);
				/*
				 * pl.setItems(paresults); event.setPatternsList(pl);
				 * System.out.println("pattern list is ::" + pl);
				 */
				for (Patterns pevent : paresults) {
					pevent.setPatternId(null);
				}
			}
			tl.setItems(tipresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return tl;
	}

	// ..................................Get, Put, Delete and Post of
	// CommodityTip........................

	public CommodityTipList getCommodityTip() throws Exception {
		List<CommodityTip> comtipresults = new ArrayList<CommodityTip>();

		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from CommodityTip")
					.getResultList();
			comtipresults = resultList;
			/*
			 * for (CommodityTip event : comtipresults) {
			 * System.out.println("CommodityTip (" +event.getTipId()+ ") : " +
			 * event.getCommodityId());
			 * 
			 * }
			 */
			comTipl.setItems(comtipresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return comTipl;
	}

	public void deleteCommodityTip(String tid, String comid) throws Exception {
		List<CommodityTip> comtipresults = new ArrayList<CommodityTip>();

		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			ComTipPk ctpk = new ComTipPk();
			ctpk.setTipId(tid);
			ctpk.setCommodityId(comid);

			CommodityTip comtip = em.find(CommodityTip.class, ctpk);
			em.remove(comtip);
			tx.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void postCommodityTip(CommodityTipList comtip) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = comtip.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	// ...........Get, Put, Delete and Post of Patterns........................

	public void postPatterns(PatternsList patlist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = patlist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void putPatterns(PatternsList plist) throws Exception {
		Patterns p = new Patterns();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = plist.getItems();
			for (int i = 0; i < result.size(); i++) {
				p = (Patterns) (result.get(i));
				Patterns pp = em.find(Patterns.class, p.getId());
				pp.setName(p.getName());
				pp.setTipId(p.getTipId());
				pp.setTypicalValue(p.getTypicalValue());

			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deletePatterns(String id) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Patterns tt = em.find(Patterns.class, id);
			em.remove(tt);
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public PatternsList getPatterns(String pid) {
		List<Patterns> paresults = new ArrayList<Patterns>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Query query1 = em.createQuery("from Patterns p where p.id = :pid");
			query1.setParameter("pid", pid);
			List resultList1 = query1.getResultList();
			paresults = resultList1;
			System.out.println("pattern id is ::" + paresults);
			for (Patterns pevent : paresults) {
				pevent.setPatternId(null);
			}
			pl.setItems(paresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return pl;
	}

	public void postOrdinal(OrdinalList ordlist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = ordlist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public OrdinalList getPattOrdinal(String pid) {
		List<Patterns> paresults = new ArrayList<Patterns>();
		List<Ordinal> ordresults = new ArrayList<Ordinal>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Query query1 = em
					.createQuery("from Ordinal o where o.patternId = :pid");
			query1.setParameter("pid", pid);
			List resultList1 = query1.getResultList();
			ordresults = resultList1;
			ol.setItems(ordresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return ol;

	}

	public PatternsList getPattern() throws Exception {
		List<Patterns> paresults = new ArrayList<Patterns>();
		List<Ordinal> ordresults = new ArrayList<Ordinal>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from Patterns").getResultList();
			paresults = resultList;
			for (Patterns event : paresults) {
				event.setPatternId(null);
				/*
				 * ordresults = event.getPatternId(); for (Ordinal oevent :
				 * ordresults) { System.out.println("ordinal (" +
				 * oevent.getOrdinal()); }
				 */
			}
			pl.setItems(paresults);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return pl;
	}

	public void putOrdinals(OrdinalList olist) throws Exception {
		Ordinal o = new Ordinal();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = olist.getItems();
			for (int i = 0; i < result.size(); i++) {
				o = (Ordinal) (result.get(i));

				OrdinalPk opk = new OrdinalPk();
				opk.setOrdinal(o.getOrdinal());
				opk.setPatternId(o.getPatternId());

				Ordinal oo = em.find(Ordinal.class, opk);
				oo.setTemplateId(o.getTemplateId());

			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteOrdinal(String pid, int ord) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			OrdinalPk opk = new OrdinalPk();
			opk.setOrdinal(ord);
			opk.setPatternId(pid);
			Ordinal por = em.find(Ordinal.class, opk);
			em.remove(por);
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	// .................Get, Put, Delete and Post of
	// Templates....................

	public TemplateMapList getTemplate() throws Exception {
		List<Template> results = new ArrayList<Template>();
		List<Role> roleresults = new ArrayList<Role>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from Template").getResultList();
			results = resultList;
			for (Template event : results) {
				event.setRoleid(null);
				event.setOrdi(null);
				/*
				 * roleresults = event.getRoleid(); for (Role roevent :
				 * roleresults) { roevent.setClassId(null); classresults =
				 * roevent.getClassId(); for (Classes clevent : classresults) {
				 * // System.out.println("Classes(" + clevent.getId() + ") : " +
				 * clevent.getName()); } }
				 */
			}
			tml.setItems(results);
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally { // tx.close();
			em.close();
			emf.close();
		}
		return tml;
	}

	public void postTemplate(TemplateMapList tmlist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = tmlist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void putTemplates(TemplateMapList tlist) throws Exception {
		Template t = new Template();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = tlist.getItems();
			for (int i = 0; i < result.size(); i++) {
				t = (Template) (result.get(i));
				Template tt = em.find(Template.class, t.getId());
				tt.setName(t.getName());
				tt.setType(t.getType());
				tt.setRdlTemplateId(t.getRdlTemplateId());
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteTemplate(String id) throws Exception {
		List<Template> results = new ArrayList<Template>();
		List<Role> roleresults = new ArrayList<Role>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Template tt = em.find(Template.class, id);
			em.remove(tt);
			tx.commit();		
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public TemplateMapList getOneTemplate(String tempid) throws Exception {
		List<Template> results = new ArrayList<Template>();
		List<Role> roleresults = new ArrayList<Role>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("from Template t where t.id = :temid");
			query.setParameter("temid", tempid);
			List resultList = query.getResultList();
			results = resultList;
			for (Template event : results) {
				event.setRoleid(null);
				event.setOrdi(null);
				/*
				 * roleresults = event.getRoleid(); for (Role roevent :
				 * roleresults) { roevent.setClassId(null); classresults =
				 * roevent.getClassId(); for (Classes clevent : classresults) {
				 * // System.out.println("Classes(" + clevent.getId() + ") : " +
				 * clevent.getName()); } }
				 */
			}
			tml.setItems(results);
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally { // tx.close();
			em.close();
			emf.close();
		}
		return tml;
	}

	public TemplateMapList getTemplateRole(String tempid) throws Exception {
		List<Role> roleresults = new ArrayList<Role>();
		List<Template> results = new ArrayList<Template>();
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("from Template t where t.id = :temid");
			query.setParameter("temid", tempid);
			List resultList = query.getResultList();
			results = resultList;
			for (Template event : results) {
				/*
				 * Query query1 =
				 * em.createQuery("from Role r where r.templateId = :temid");
				 * query1.setParameter("temid", tempid); List resultList1 =
				 * query1.getResultList(); roleresults = resultList1;
				 */
				event.setOrdi(null);
				roleresults = event.getRoleid();
				for (Role roevent : roleresults) {
					roevent.setClassId(null);

				}
			}
			tml.setItems(results);
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally { // tx.close();
			em.close();
			emf.close();
		}
		return tml;
	}

	// ...................Get, Put, Delete and Post of Roles............................

	public RoleList getTemplateParticularRole(String tempid, String rid)
			throws Exception {
		List<Role> roleresults = new ArrayList<Role>();
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Query query1 = em
					.createQuery("from Role r where r.templateId = :temid and r.id = :rid");
			query1.setParameter("temid", tempid);
			query1.setParameter("rid", rid);
			List resultList1 = query1.getResultList();
			roleresults = resultList1;
			for (Role roevent : roleresults) {
				classresults = roevent.getClassId();
				for (Classes clevent : classresults) {
				}
				rml.setItems(roleresults);
			}
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return rml;
	}

	public RoleList getRoles() throws Exception {
		List<Role> roleresults = new ArrayList<Role>();
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from Role").getResultList();
			roleresults = resultList;
			for (Role roevent : roleresults) {
				roevent.setClassId(null);
				rml.setItems(roleresults);
			}
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return rml;
	}

	public void postRole(RoleList rolist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = rolist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void putRoles(RoleList rlist) throws Exception {
		Role r = new Role();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = rlist.getItems();
			for (int i = 0; i < result.size(); i++) {
				r = (Role) (result.get(i));

				RolePk rpk = new RolePk();
				rpk.setId(r.getId());
				rpk.setTemplateId(r.getTemplateId());

				Role rr = em.find(Role.class, rpk);
				rr.setName(r.getName());
				rr.setRdlRoleId(r.getRdlRoleId());
				rr.setType(r.getId());
				rr.setClazz(r.getClazz());
				rr.setIndividual(r.getIndividual());
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteRoles(String tid, String rid) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			RolePk rpk = new RolePk();
			rpk.setId(rid);
			rpk.setTemplateId(tid);

			Role rr = em.find(Role.class, rpk);
			em.remove(rr);
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public RoleList getRole(String rid) throws Exception {
		List<Role> roleresults = new ArrayList<Role>();
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Query query1 = em.createQuery("from Role r where r.id = :rid");
			query1.setParameter("rid", rid);
			List resultList = query1.getResultList();
			roleresults = resultList;
			for (Role roevent : roleresults) {
				classresults = roevent.getClassId();
				for (Classes clevent : classresults) {
					clevent.setRoleId(null);
				}
				rml.setItems(roleresults);
			}
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return rml;
	}

	// ..................Get, Put, Delete and Post of Classes............

	public ClassesList getClasses() throws Exception {
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			List resultList = em.createQuery("from Classes").getResultList();
			classresults = resultList;
			for (Classes clevent : classresults) {
				clevent.setRoleId(null);
			}
			cl.setItems(classresults);
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return cl;
	}

	public ClassesList getClasses(String cid) throws Exception {
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Query query1 = em.createQuery("from Classes c where c.id = :cid");
			query1.setParameter("cid", cid);
			List resultList = query1.getResultList();
			classresults = resultList;
			for (Classes clevent : classresults) {
				clevent.setRoleId(null);
			}
			cl.setItems(classresults);
		} catch (PersistenceException e) {

			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return cl;
	}

	public void postClass(ClassesList cllist) throws Exception {
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = cllist.getItems();
			for (int i = 0; i < result.size(); i++) {
				em.persist(result.get(i));
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteClasses(String id) throws Exception {
		List<Role> roleresults = new ArrayList<Role>();
		List<Classes> classresults = new ArrayList<Classes>();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			
			  Classes c = em.find(Classes.class, id);
			  em.remove(c);
			  tx.commit();
			 
			/*Query query1 = em.createQuery("from Classes c where c.id = :cid");
			query1.setParameter("cid", id);
			List resultList = query1.getResultList();
			classresults = resultList;
			for (Classes clevent : classresults)
			{
				roleresults = clevent.getRoleId();
				for (Role revent : roleresults) {
					em.remove(revent);
				}
				em.remove(clevent);
			}S
			tx.commit();*/
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void putClasses(ClassesList clist) throws Exception {
		Classes c = new Classes();
		try {
			emf = Persistence.createEntityManagerFactory("Tips-Jpa");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List result = clist.getItems();
			for (int i = 0; i < result.size(); i++) {
				c = (Classes) (result.get(i));
				Classes cc = em.find(Classes.class, c.getId());
				cc.setName(c.getName());
				cc.setRdlClassId(c.getRdlClassId());
			}
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	/*
	 * public OrdinalList getTipPattOrdinal(String id, String pid) {
	 * List<Patterns> paresults = new ArrayList<Patterns>(); List<Ordinal>
	 * ordresults = new ArrayList<Ordinal>(); try { emf =
	 * Persistence.createEntityManagerFactory("Tips-Jpa"); em =
	 * emf.createEntityManager(); tx = em.getTransaction(); tx.begin(); Query
	 * query =
	 * em.createQuery("from Patterns p where p.tipId = :id and p.id = :pid");
	 * query.setParameter("id", id); query.setParameter("pid", pid); List
	 * resultList = query.getResultList(); paresults = resultList; for (Patterns
	 * event : paresults) { ordresults = event.getPatternId(); for (Ordinal
	 * oevent : ordresults) {} } ol.setItems(ordresults); } catch
	 * (PersistenceException e) { e.printStackTrace(); } finally {//
	 * ((EntityManager) tx).close(); em.close(); emf.close(); } return ol;
	 * 
	 * }
	 */

	/*
	 * public TemplateMaps getTest() throws Exception { List<TipsTest> results =
	 * null; TipsTest tt = new TipsTest(); List<TestRole> results1 = null; try {
	 * emf = Persistence.createEntityManagerFactory("Tips-Jpa"); em =
	 * emf.createEntityManager(); System.out.println("=======");
	 * EntityTransaction tx = em.getTransaction(); tx.begin(); List resultList =
	 * em.createQuery("from TipsTest").getResultList(); results = resultList;
	 * System.out.println(results); // tms.settItems(results);
	 * System.out.println("======= mapping role to patterns"); List resultList1
	 * = em.createQuery("from TestRole").getResultList(); results1 = resultList;
	 * System.out.println(results1); // tms.settItems(results); for (TestRole
	 * event : results1) { System.out.println("TestRole (" + event.getId() +
	 * ") : " + event.getName() + "::" );
	 * 
	 * 
	 * } // em.persist(account); // tx.commit(); // List<TestRole> results =
	 * null; //TipsTest tt = new TipsTest(); // try // { // emf =
	 * Persistence.createEntityManagerFactory("Tips-Jpa"); // em =
	 * emf.createEntityManager(); System.out.println("======="); //
	 * EntityTransaction tx = em.getTransaction(); // tx.begin(); // List
	 * resultList = em.createQuery("from TestRole").getResultList(); // results
	 * = resultList; // System.out.println(results); // //
	 * tms.setItems(results); // for (TestRole event : results) // { //
	 * System.out.println("TestRole (" + event.getId() + ") : " +
	 * event.getName() + "::" + event.getSeqrole()); // // } //
	 * em.persist(account); // tx.commit(); // } } catch (PersistenceException
	 * e) {
	 * 
	 * e.printStackTrace(); } finally { em.close(); emf.close(); } return tms; }
	 */
}
