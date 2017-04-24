package com.ninehcom.newsserver.mapper;

import com.ninehcom.newsserver.entity.Editconfig;

import java.util.List;

/**
 * Editconfig的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface EditconfigMapper {

    List<Editconfig> selectAllEditconfig();
    Editconfig selectEditconfig(String key);
    int createEditConfigTable();
    int initEditConfig(Editconfig config);
    int createUserInfoTable();
    
    int createActionTable();
    int createUserStatisticsTable();
    int createUserActionTable();
    int createUserScoreTable();
    int createVersionTable();

    int initAction(int id, String name, String description, int score, int expressience);
    int initVersion(int id, int type, String typeName, String version);

}
