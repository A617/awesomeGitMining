package main.dao.impl;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.po.UserPO;
import net.sf.json.JSONObject;

public class UserDaoImpl implements IUserDao {

	@Override
	public UserPO getUser(String login) {
		String s = HttpRequest.sendGet("http://gitmining.net/api/user/", login);
		UserPO po =JsonUtil.<UserPO>parseJson2PO(JSONObject.fromObject(s), UserPO.class);

		return po;
	}

}
