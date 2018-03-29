/* MegaMek - Copyright (C) 2004,2005 Ben Mazur (bmazur@sev.org)
 * 
 *  This program is free software; you can redistribute it and/or modify it 
 *  under the terms of the GNU General Public License as published by the Free 
 *  Software Foundation; either version 2 of the License, or (at your option) 
 *  any later version.
 * 
 *  This program is distributed in the hope that it will be useful, but 
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 *  for more details.
 */
/*
 * Created on Sep 25, 2004
 *
 */
package megamek.common.weapons.capitalweapons;

import megamek.common.AmmoType;
import megamek.common.IGame;
import megamek.common.ToHitData;
import megamek.common.actions.WeaponAttackAction;
import megamek.common.weapons.AttackHandler;
import megamek.common.weapons.WhiteSharkHandler;
import megamek.common.weapons.WhiteSharkTHandler;
import megamek.server.Server;

/**
 * @author Jay Lawson
 */
public class CapMissTeleWhiteSharkWeapon extends CapitalMissileWeapon {
    /**
     * 
     */
    private static final long serialVersionUID = 8756042527483383101L;

    /**
     * 
     */
    public CapMissTeleWhiteSharkWeapon() {
        super();
        this.name = "Tele-operated Missile (White Shark-T)";
        this.setInternalName(this.name);
        this.addLookupName("WhiteSharkT");
        this.heat = 15;
        this.damage = 3;
        this.ammoType = AmmoType.T_WHITE_SHARK_T;
        this.shortRange = 12;
        this.mediumRange = 24;
        this.longRange = 36;
        this.extremeRange = 48;
        this.tonnage = 130.0f;
        this.bv = 692;
        this.cost = 145000;
        this.flags = flags.or(F_MISSILE);
        this.atClass = CLASS_TELE_MISSILE;
        this.shortAV = 3;
        this.medAV = 3;
        this.longAV = 3;
        this.extAV = 3;
        this.missileArmor = 30;
        this.maxRange = RANGE_EXT;
        rulesRefs = "210,TM";
        techAdvancement.setTechBase(TECH_BASE_IS)
        	.setIntroLevel(false)
        	.setUnofficial(false)
            .setTechRating(RATING_F)
            .setAvailability(RATING_X, RATING_X, RATING_E, RATING_D)
            .setISAdvancement(3053, 3056, 3060, DATE_NONE, DATE_NONE)
            .setISApproximate(false, false, false,false, false)
            .setPrototypeFactions(F_CS,F_DC)
            .setProductionFactions(F_DC);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * megamek.common.weapons.Weapon#getCorrectHandler(megamek.common.ToHitData,
     * megamek.common.actions.WeaponAttackAction, megamek.common.IGame)
     */
    @Override
    protected AttackHandler getCorrectHandler(ToHitData toHit,
            WeaponAttackAction waa, IGame game, Server server) {
        AmmoType atype = (AmmoType) game.getEntity(waa.getEntityId())
                .getEquipment(waa.getWeaponId()).getLinked().getType();
        if (atype.hasFlag(AmmoType.F_TELE_MISSILE) && game.getBoard().inSpace())
            return new WhiteSharkTHandler(toHit, waa, game, server);
        return new WhiteSharkHandler(toHit, waa, game, server);
    }
}
