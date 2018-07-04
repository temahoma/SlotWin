package com.luyunfeng.outsource.slotwin.bean.greendao;

import android.util.Log;

import com.luyunfeng.outsource.slotwin.bean.Prefecture;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by luyunfeng on 2018/7/4.
 */

public class PrefectureTable {
    private static final String TAG = PrefectureTable.class.getSimpleName();
    private DaoManager mManager;

    public PrefectureTable(){
        mManager = DaoManager.getInstance();
    }

    /**
     * 完成prefecture记录的插入，如果表未创建，先创建Prefecture表
     * @param prefecture
     * @return
     */
    public boolean insertPrefecture(Prefecture prefecture){
        boolean flag = false;
        flag = mManager.getDaoSession().getPrefectureDao().insert(prefecture) == -1 ? false : true;
        Log.i(TAG, "insert Prefecture :" + flag + "-->" + prefecture.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param prefectureList
     * @return
     */
    public boolean insertMultiPrefecture(final List<Prefecture> prefectureList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Prefecture prefecture : prefectureList) {
                        mManager.getDaoSession().insertOrReplace(prefecture);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param prefecture
     * @return
     */
    public boolean updatePrefecture(Prefecture prefecture){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(prefecture);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param prefecture
     * @return
     */
    public boolean deletePrefecture(Prefecture prefecture){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(prefecture);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(Prefecture.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Prefecture> queryAllPrefecture(){
        return mManager.getDaoSession().loadAll(Prefecture.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Prefecture queryPrefectureById(long key){
        return mManager.getDaoSession().load(Prefecture.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<Prefecture> queryPrefectureByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Prefecture.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<Prefecture> queryPrefectureByQueryBuilder(long id){
        QueryBuilder<Prefecture> queryBuilder = mManager.getDaoSession().queryBuilder(Prefecture.class);
        return queryBuilder.where(PrefectureDao.Properties.Id.eq(id)).list();
    }
}
