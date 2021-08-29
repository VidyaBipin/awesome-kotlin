/*
 * This file is generated by jOOQ.
 */
package link.kotlin.server.jooq.main.keys


import link.kotlin.server.jooq.main.tables.FlywaySchemaHistory
import link.kotlin.server.jooq.main.tables.Kotliner
import link.kotlin.server.jooq.main.tables.KotlinerMeta
import link.kotlin.server.jooq.main.tables.records.FlywaySchemaHistoryRecord
import link.kotlin.server.jooq.main.tables.records.KotlinerMetaRecord
import link.kotlin.server.jooq.main.tables.records.KotlinerRecord

import org.jooq.ForeignKey
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal



// -------------------------------------------------------------------------
// UNIQUE and PRIMARY KEY definitions
// -------------------------------------------------------------------------

val FLYWAY_SCHEMA_HISTORY_PK: UniqueKey<FlywaySchemaHistoryRecord> = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), arrayOf(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK), true)
val KOTLINER_PKEY: UniqueKey<KotlinerRecord> = Internal.createUniqueKey(Kotliner.KOTLINER, DSL.name("kotliner_pkey"), arrayOf(Kotliner.KOTLINER.ID), true)
val UNIQUE_KOTLINER_EMAIL: UniqueKey<KotlinerRecord> = Internal.createUniqueKey(Kotliner.KOTLINER, DSL.name("unique_kotliner_email"), arrayOf(Kotliner.KOTLINER.NORMALIZED_EMAIL), true)
val UNIQUE_KOTLINER_NICKNAME: UniqueKey<KotlinerRecord> = Internal.createUniqueKey(Kotliner.KOTLINER, DSL.name("unique_kotliner_nickname"), arrayOf(Kotliner.KOTLINER.NICKNAME), true)
val KOTLINER_META_PKEY: UniqueKey<KotlinerMetaRecord> = Internal.createUniqueKey(KotlinerMeta.KOTLINER_META, DSL.name("kotliner_meta_pkey"), arrayOf(KotlinerMeta.KOTLINER_META.ID), true)
val UNIQUE_KOTLINER_META: UniqueKey<KotlinerMetaRecord> = Internal.createUniqueKey(KotlinerMeta.KOTLINER_META, DSL.name("unique_kotliner_meta"), arrayOf(KotlinerMeta.KOTLINER_META.META_KEY, KotlinerMeta.KOTLINER_META.KOTLINER_ID), true)

// -------------------------------------------------------------------------
// FOREIGN KEY definitions
// -------------------------------------------------------------------------

val KOTLINER_META__KOTLINER_META_KOTLINER_ID_FKEY: ForeignKey<KotlinerMetaRecord, KotlinerRecord> = Internal.createForeignKey(KotlinerMeta.KOTLINER_META, DSL.name("kotliner_meta_kotliner_id_fkey"), arrayOf(KotlinerMeta.KOTLINER_META.KOTLINER_ID), link.kotlin.server.jooq.main.keys.KOTLINER_PKEY, arrayOf(Kotliner.KOTLINER.ID), true)
