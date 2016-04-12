package br.com.ejb.beans;

import br.com.ejb.model.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TeamBeanImpl implements GenericRemoteBean<Team, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Team save(final Team team) {
		if (team.getId() == null) {
			this.em.persist(team);
		} else {
			return this.em.merge(team);
		}
		return team;
	}

	@Override
	public void delete(final Integer id) {
		this.em.remove(this.findById(id));
	}

	@Override
	public Team findById(final Integer id) {
		return this.em.find(Team.class, id);
	}

	@Override
	public List<Team> findAll() {
		return this.em.createNamedQuery("Team.findAll", Team.class).getResultList();
	}
}
