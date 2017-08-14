package com.ffcs.icity.mvc.backstage.gamebili.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.gamebili.dao.GameBiliDao;
import com.ffcs.icity.mvc.backstage.gamebili.entity.GameBili;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/gameBili")
@Controller
public class GameBiliController extends AsynController<GameBili>{

	private GameBiliDao gameBiliDao;

	@Override
	protected CRUDDao<GameBili> getDao() {
		return this.gameBiliDao;
	}

	@Autowired
	public void setGameBiliDao(GameBiliDao gameBiliDao) {
		this.gameBiliDao = gameBiliDao;
	}

	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/gameBili";
	}
}
