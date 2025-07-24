package com.hmdp.service.impl;

import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import static com.hmdp.utils.RedisConstants.*;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryList() {
        List<String> list = stringRedisTemplate.opsForList().range(CACHE_SHOP_TYPE_LIST_KEY, 0, -1);
        if (list != null && list.size() > 0) {
            ArrayList<ShopType> typeList = new ArrayList<>();
            for (String str : list) {
                typeList.add(JSONUtil.toBean(str, ShopType.class));
            }
            return Result.ok(typeList);
        }
        // 从数据库中查询

        List<ShopType> typeList = query().orderByAsc("sort").list();
        if (typeList == null || typeList.isEmpty()) {
            // 3.1.数据库中也不存在，则返回 false
            return Result.fail("分类不存在！");
        }

        // 缓存到redis
        for (ShopType shopType : typeList) {
            stringRedisTemplate.opsForList().rightPushAll(CACHE_SHOP_TYPE_LIST_KEY, JSONUtil.toJsonStr(shopType));
        }
        // 返回结果
        return Result.ok(typeList);

    }

}
