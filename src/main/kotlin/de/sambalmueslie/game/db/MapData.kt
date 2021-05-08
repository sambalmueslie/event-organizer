package de.sambalmueslie.game.db

import de.sambalmueslie.common.CrudEntity
import de.sambalmueslie.game.api.Map
import de.sambalmueslie.game.api.MapChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Map")
@Table(name = "game_map")
data class MapData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var name: String = "",
) : CrudEntity<Map, MapChangeRequest> {
    override fun convert() = Map(id, name)

    companion object {
        fun convert(request: MapChangeRequest) = MapData(0, request.name)
    }

    override fun update(request: MapChangeRequest) {
        name = request.name
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
